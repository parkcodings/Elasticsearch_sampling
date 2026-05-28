package com.example.elasticsearch_sampling.repository;

import com.example.elasticsearch_sampling.domain.BookDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookSearchRepository
        extends ElasticsearchRepository<BookDocument, String>, BookSearchRepositoryCustom {
}
