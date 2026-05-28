package com.example.elasticsearch_sampling.repository;

import com.example.elasticsearch_sampling.domain.BookDocument;
import java.util.List;

public interface BookSearchRepositoryCustom {

    List<BookDocument> searchByKeyword(String keyword);
}
