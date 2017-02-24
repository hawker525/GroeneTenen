package be.vdab.repositories;

import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.PostcodeReeks;

import java.util.List;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
public interface FiliaalRepository {
    void create(Filiaal filiaal);
    Optional<Filiaal> read(long id);
    void update(Filiaal filiaal);
    void delete(long id);
    List<Filiaal> findAll();
    long findAantalFilialen();
    // het aantal werknemers van een filiaal:
    long findAantalWerknemers(long id);
    List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks);
}
