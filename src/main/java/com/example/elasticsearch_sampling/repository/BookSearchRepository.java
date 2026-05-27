package com.example.elasticsearch_sampling.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.example.elasticsearch_sampling.domain.BookDocument;

public interface BookSearchRepository extends ElasticsearchRepository<BookDocument, String> {

    List<BookDocument> findByTitleContainingOrAuthorContaining(String titleKeyword, String authorKeyword);

}
