package be.vdab.services;

import be.vdab.entities.Werknemer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Maarten Westelinck on 6/03/2017 for groenetenen.
 */
public interface WerknemerService {
    Page<Werknemer> findAll(Pageable pageable);
}
