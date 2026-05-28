package com.example.elasticsearch_sampling.repository;

import com.example.elasticsearch_sampling.domain.BookDocument;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookSearchRepositoryImpl implements BookSearchRepositoryCustom {

    private final ElasticsearchOperations operations;

    @Override
    public List<BookDocument> searchByKeyword(String keyword) {
        Query query = NativeQuery.builder()
                .withQuery(q -> q.multiMatch(m -> m
                        .query(keyword)
                        .fields("title", "author")
                        .fuzziness("AUTO")
                        .prefixLength(1)
                        .maxExpansions(10)))
                .build();

        return operations.search(query, BookDocument.class)
                .stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
