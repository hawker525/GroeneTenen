package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Maarten Westelinck on 2/02/2017 for groenetenen.
 */
@Controller
@RequestMapping("/werknemers")
class WerknemerController {
    private static final String WERKNEMERS_VIEW = "werknemers/werknemers";

    @GetMapping
    String findAll() {
        return WERKNEMERS_VIEW;
    }
}
