package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Persoon;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Persoon} entity.
 */
public interface PersoonSearchRepository extends ElasticsearchRepository<Persoon, Long> {
}
