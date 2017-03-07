package be.vdab.web;

import be.vdab.entities.Filiaal;
import be.vdab.exception.FiliaalHeeftNogWerknemersException;
import be.vdab.services.FiliaalService;
import be.vdab.valueobjects.PostcodeReeks;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Maarten Westelinck on 2/02/2017 for groenetenen.
 *
 */
@Controller
@RequestMapping("/filialen")
class FiliaalController {
    private static final String FILIALEN_VIEW = "filialen/filialen";
    private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
    private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
    private static final String FILIAAL_VIEW = "filialen/filiaal";
    private static final String REDIRECT_URL_FILIAAL_NIET_GEVONDEN = "redirect:/filialen";
    private static final String REDIRECT_URL_NA_VERWIJDEREN = "redirect:/filialen/{id}/verwijderd";
    private static final String REDIRECT_URL_HEEFT_NOG_WERKNEMERS = "redirect:/filialen/{id}";
    private static final String VERWIJDERD_VIEW = "filialen/verwijderd";
    private static final String PER_POSTCODE_VIEW = "filialen/perpostcode";
    private static final String WIJZIGEN_VIEW = "filialen/wijzigen";
    private static final String REDIRECT_URL_NA_WIJZIGEN = "redirect:/filialen";
    private static final String AFSCHRIJVEN_VIEW = "filialen/afschrijven";
    private static final String REDIRECT_NA_AFSCHRIJVEN = "redirect:/";
    private final FiliaalService filiaalService;
    private static final String REDIRECT_URL_NA_LOCKING_EXCEPTION ="redirect:/filialen/{id}?optimisticlockingexception=true";

    public FiliaalController(FiliaalService filiaalService){
        this.filiaalService = filiaalService;
    }

    @GetMapping("afschrijven")
    ModelAndView afschrijvenForm() {
        return new ModelAndView(AFSCHRIJVEN_VIEW, "filialen", filiaalService.findNietAfgeschreven()).addObject(new AfschrijvenForm());
    }

    @PostMapping("afschrijven")
    ModelAndView afschrijven(@Valid AfschrijvenForm afschrijvenForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ModelAndView(AFSCHRIJVEN_VIEW, "filialen", filiaalService.findNietAfgeschreven());
        }
        filiaalService.afschrijven(afschrijvenForm.getFilialen());
        return new ModelAndView(REDIRECT_NA_AFSCHRIJVEN);
    }

    @GetMapping
    ModelAndView findAll() {
        return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll()).addObject("aantalFilialen", filiaalService.findAantalFilialen());
    }

    @GetMapping("{filiaal}/wijzigen")
    ModelAndView updateForm(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            return new ModelAndView(REDIRECT_URL_FILIAAL_NIET_GEVONDEN);
        }
        return new ModelAndView(WIJZIGEN_VIEW).addObject(filiaal);
    }

    @PostMapping("{id}/wijzigen")
    String update(@Valid Filiaal filiaal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return WIJZIGEN_VIEW;
        }
        try {
            filiaalService.update(filiaal);
            return REDIRECT_URL_NA_WIJZIGEN;
        } catch (ObjectOptimisticLockingFailureException ex) {
            return REDIRECT_URL_NA_LOCKING_EXCEPTION;
        }
    }

    @InitBinder("postcodeReeks")
    void initBinderPostcodeReeks(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @InitBinder("filiaal")
    void initBinderFiliaal(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @GetMapping(params = {"vanpostcode", "totpostcode"})
    ModelAndView findPostcodeReeks(@Valid PostcodeReeks reeks, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView(PER_POSTCODE_VIEW);
        if (!bindingResult.hasErrors()) {
            List<Filiaal> filialen = filiaalService.findByPostcodeReeks(reeks);
            if (filialen.isEmpty()) {
                bindingResult.reject("geenFilialen");
            } else {
                modelAndView.addObject("filialen", filialen);
            }
        }
        return modelAndView;
    }

    @GetMapping("perpostcode")
    ModelAndView findByPostcodeReeks() {
        PostcodeReeks reeks = new PostcodeReeks();
//        reeks.setVanpostcode(1000);
//        reeks.setTotpostcode(9999);
        return new ModelAndView(PER_POSTCODE_VIEW).addObject(reeks);
    }


    @GetMapping("{filiaal}")
    ModelAndView read(@PathVariable Filiaal filiaal) {
        ModelAndView modelAndView = new ModelAndView(FILIAAL_VIEW);
        if (filiaal != null) {
            modelAndView.addObject(filiaal);
        }
        return modelAndView;
    }

    @GetMapping("toevoegen")
    ModelAndView createForm() {
        return new ModelAndView(TOEVOEGEN_VIEW, "filiaal", new Filiaal());
    }

    @PostMapping("")
    String create(@Valid Filiaal filiaal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return TOEVOEGEN_VIEW;
        }
        filiaalService.create(filiaal);
        return REDIRECT_URL_NA_TOEVOEGEN;
    }

    @PostMapping("{filiaal}/verwijderen")
    String delete(@PathVariable Filiaal filiaal, RedirectAttributes redirectAttributes) {
        if (filiaal == null) {
            return REDIRECT_URL_FILIAAL_NIET_GEVONDEN;
        }
        long id = filiaal.getId();
        try {
            filiaalService.delete(id);
            redirectAttributes.addAttribute("id", id)
.addAttribute("naam", filiaal.getNaam());
            return REDIRECT_URL_NA_VERWIJDEREN;
        } catch (FiliaalHeeftNogWerknemersException ex) {
            redirectAttributes.addAttribute("id", id)
                    .addAttribute("fout", "Filiaal heeft nog werknemers");
            return REDIRECT_URL_HEEFT_NOG_WERKNEMERS;
        }
    }

    @GetMapping("{id}/verwijderd")
    String deleted(String naam) {
        return VERWIJDERD_VIEW;
    }

}
