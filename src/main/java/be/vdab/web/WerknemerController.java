package be.vdab.web;

import be.vdab.services.WerknemerService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Maarten Westelinck on 2/02/2017 for groenetenen.
 */
@Controller
@RequestMapping("/werknemers")
class WerknemerController {
    private static final String WERKNEMERS_VIEW = "werknemers/werknemers";
    private final WerknemerService werknemerService;


    public WerknemerController(WerknemerService werknemerService) {
        this.werknemerService = werknemerService;
    }

    @GetMapping
    ModelAndView findAll(Pageable pageable) {
        return new ModelAndView(WERKNEMERS_VIEW, "page", werknemerService.findAll(pageable));
    }
}
