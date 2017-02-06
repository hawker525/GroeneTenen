package be.vdab.services;

import be.vdab.entities.Filiaal;
import be.vdab.exception.FiliaalHeeftNogWerknemersException;
import be.vdab.repositories.FiliaalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
@Service
public class DefaultFiliaalService implements FiliaalService{
    private final FiliaalRepository filiaalRepository;
    DefaultFiliaalService(FiliaalRepository filiaalRepository) {
        this.filiaalRepository = filiaalRepository;
    }
    @Override
    public void create(Filiaal filiaal) {
        filiaalRepository.create(filiaal);
    }
    @Override
    public Optional<Filiaal> read(long id) {
        return filiaalRepository.read(id);
    }
    @Override
    public void update(Filiaal filiaal) {
        filiaalRepository.update(filiaal);
    }

    @Override
    public void delete(long id) {
        if (filiaalRepository.findAantalWerknemers(id) != 0) {
            throw new FiliaalHeeftNogWerknemersException();
        }
        filiaalRepository.delete(id);
    }
    @Override
    public List<Filiaal> findAll() {
        return filiaalRepository.findAll();
    }
    @Override
    public long findAantalFilialen() {
        return filiaalRepository.findAantalFilialen();
    }
}
