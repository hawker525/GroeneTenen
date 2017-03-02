package be.vdab.services;

import be.vdab.entities.Filiaal;
import be.vdab.exception.FiliaalHeeftNogWerknemersException;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

import java.util.List;
import java.util.Optional;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
@ReadOnlyTransactionalService
public class DefaultFiliaalService implements FiliaalService{

    private final FiliaalRepository filiaalRepository;

    DefaultFiliaalService(FiliaalRepository filiaalRepository) {
        this.filiaalRepository = filiaalRepository;
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void create(Filiaal filiaal) {
        filiaalRepository.create(filiaal);
    }

    @Override
    public Optional<Filiaal> read(long id) {
        return filiaalRepository.read(id);
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void update(Filiaal filiaal) {
        filiaalRepository.update(filiaal);
    }

    @Override
    @ModifyingTransactionalServiceMethod
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

    @Override
    public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
        return filiaalRepository.findByPostcodeReeks(reeks);
    }
}
