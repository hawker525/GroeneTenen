package be.vdab.web;

import be.vdab.exception.KanKoersNietLezenException;
import be.vdab.services.EuroService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * Created by Maarten Westelinck on 9/03/2017 for groenetenen.
 */
@Controller
@RequestMapping("euro")
class EuroController {
    private final static String VIEW = "euro/naardollar";
    private final EuroService euroService;

    EuroController(EuroService euroService) {
        this.euroService = euroService;
    }

    @GetMapping("{euro}/naardollar")
    ModelAndView naarDollar(@PathVariable BigDecimal euro) {
        ModelAndView modelAndView = new ModelAndView(VIEW);
        try {
            modelAndView.addObject("dollar", euroService.naarDollar(euro));
        } catch (KanKoersNietLezenException ex) {
            modelAndView.addObject("fout", "Kan koers niet lezen");
        }
        return modelAndView;
    }
}
