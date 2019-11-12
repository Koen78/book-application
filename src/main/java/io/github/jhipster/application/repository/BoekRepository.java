package io.github.jhipster.application.repository;
import io.github.jhipster.application.domain.Boek;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Boek entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BoekRepository extends JpaRepository<Boek, Long> {

}
