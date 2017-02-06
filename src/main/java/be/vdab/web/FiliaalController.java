package be.vdab.web;

import be.vdab.services.FiliaalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * Created by Maarten Westelinck on 2/02/2017 for groenetenen.
 */
@Controller
@RequestMapping("/filialen")
class FiliaalController {
    private static final String FILIALEN_VIEW = "filialen/filialen";
    private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
    private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
    private static final Logger LOGGER = Logger.getLogger(FiliaalController.class.getName());
    private final FiliaalService filiaalService;

    public FiliaalController(FiliaalService filiaalService){
        this.filiaalService = filiaalService;
    }

    @GetMapping
    ModelAndView findAll() {
        return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll());
    }

    @GetMapping("toevoegen")
    String createForm() {
        return TOEVOEGEN_VIEW;
    }

    @PostMapping()
    String create() {
        LOGGER.info("filiaal record toevoegen aan database");
        return REDIRECT_URL_NA_TOEVOEGEN;
    }
}
