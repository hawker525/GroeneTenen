package be.vdab.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by maarten on 12/03/2017.
 */
@ControllerAdvice
public class FoutController {
    private static final String VIEW = "fout";

    @ExceptionHandler(Exception.class)
    public String foutPagina() {
        return VIEW;
    }
}
