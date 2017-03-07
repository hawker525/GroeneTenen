package be.vdab.restservices;

import be.vdab.entities.Filiaal;
import be.vdab.exception.FiliaalHeeftNogWerknemersException;
import be.vdab.exception.FiliaalNietGevondenException;
import be.vdab.services.FiliaalService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maarten Westelinck on 7/03/2017 for groenetenen.
 *
 */
@RestController
@RequestMapping("/filialen")
public class FiliaalRestController {
    private final FiliaalService filiaalService;

    FiliaalRestController(FiliaalService filiaalService) {
        this.filiaalService = filiaalService;
    }

    @GetMapping("{filiaal}")
    Filiaal read(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        return filiaal;
    }

    @DeleteMapping("{filiaal}")
    void delete(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        filiaalService.delete(filiaal.getId());
    }

    @PostMapping
    void create(@RequestBody @Valid Filiaal filiaal) {
        filiaalService.create(filiaal);
    }

    @PutMapping("{id}")
    void update(@RequestBody @Valid Filiaal filiaal) {
        filiaalService.update(filiaal);
    }

    @RequestMapping(path = "{filiaal}", method = RequestMethod.OPTIONS)
    HttpHeaders options(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.PUT);
        if(filiaal.getWerknemers().isEmpty()) allowedMethods.add(HttpMethod.DELETE);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAllow(allowedMethods);
        return httpHeaders;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String filiaalMetVerkeerdeProperties(MethodArgumentNotValidException ex) {
        StringBuilder fouten = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fouten.append(error.getField()).append(':')
                .append(error.getDefaultMessage()).append('\n'));
        fouten.deleteCharAt(fouten.length() - 1);
        return fouten.toString();
    }

    @ExceptionHandler(FiliaalHeeftNogWerknemersException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String filiaalHeeftNogWerknemers() {
        return "filiaal heeft nog werknemers";
    }

    @ExceptionHandler(FiliaalNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void filiaalNietGevonden(){}
}
