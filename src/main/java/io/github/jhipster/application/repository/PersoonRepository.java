package io.github.jhipster.application.repository;
import io.github.jhipster.application.domain.Persoon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Persoon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersoonRepository extends JpaRepository<Persoon, Long> {

}
