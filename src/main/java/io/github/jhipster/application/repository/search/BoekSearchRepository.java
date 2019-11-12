package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Boek;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Boek} entity.
 */
public interface BoekSearchRepository extends ElasticsearchRepository<Boek, Long> {
}
