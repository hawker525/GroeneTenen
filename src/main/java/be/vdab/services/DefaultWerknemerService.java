package be.vdab.services;

import be.vdab.entities.Werknemer;
import be.vdab.repositories.WerknemerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Maarten Westelinck on 6/03/2017 for groenetenen.
 */
@ReadOnlyTransactionalService
public class DefaultWerknemerService implements WerknemerService{

    private final WerknemerRepository werknemerRepository;

    public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
        this.werknemerRepository = werknemerRepository;
    }

    @Override
    public Page<Werknemer> findAll(Pageable pageable) {
        return werknemerRepository.findAll(pageable);
    }
}
