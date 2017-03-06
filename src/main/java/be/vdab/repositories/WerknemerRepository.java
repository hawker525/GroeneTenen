package be.vdab.repositories;

import be.vdab.entities.Werknemer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Maarten Westelinck on 6/03/2017 for groenetenen.
 */
public interface WerknemerRepository extends JpaRepository<Werknemer, Long>{

    @Override
    @EntityGraph("Werknemer.metFiliaal")
    List<Werknemer> findAll(Sort sort);

    @Override
    @EntityGraph("Werknemer.metFiliaal")
    Page<Werknemer> findAll(Pageable pageable);
}
