package be.vdab.repositories;

import be.vdab.entities.Filiaal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 *
 */
public interface FiliaalRepository extends JpaRepository<Filiaal, Long>{
    List<Filiaal> findByAdresPostcodeBetweenOrderByNaam(int van, int tot);
    List<Filiaal> findByWaardeGebouwNot(BigDecimal waarde);
}
