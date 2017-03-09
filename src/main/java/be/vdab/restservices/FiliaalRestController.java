package be.vdab.restservices;

import be.vdab.entities.Filiaal;
import be.vdab.exception.FiliaalHeeftNogWerknemersException;
import be.vdab.exception.FiliaalNietGevondenException;
import be.vdab.services.FiliaalService;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Maarten Westelinck on 7/03/2017 for groenetenen.
 *
 */
@RestController
@ExposesResourceFor(Filiaal.class)
@RequestMapping("/filialen")
public class FiliaalRestController {
    private final FiliaalService filiaalService;
    private final EntityLinks entityLinks;

    FiliaalRestController(FiliaalService filiaalService, EntityLinks entityLinks) {
        this.filiaalService = filiaalService;
        this.entityLinks = entityLinks;
    }

    @GetMapping
    FilialenResource findAll() {
        return new FilialenResource(filiaalService.findAll(), entityLinks);
    }

    @GetMapping("{filiaal}")
    FiliaalResource read(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        return new FiliaalResource(filiaal, entityLinks);
    }

    @DeleteMapping("{filiaal}")
    void delete(@PathVariable Filiaal filiaal) {
        if (filiaal == null) {
            throw new FiliaalNietGevondenException();
        }
        filiaalService.delete(filiaal.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    HttpHeaders create(@RequestBody @Valid Filiaal filiaal) {
        filiaalService.create(filiaal);
        HttpHeaders headers =  new HttpHeaders();
        Link link = entityLinks.linkToSingleResource(Filiaal.class, filiaal.getId());
        headers.setLocation(URI.create(link.getHref()));
        return headers;
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
