package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Maarten Westelinck on 2/02/2017 for groenetenen.
 */
@Controller
@RequestMapping("/")
class IndexController {
    private static final String VIEW = "index";

    @GetMapping
    String index() {
        return VIEW;
    }
}
