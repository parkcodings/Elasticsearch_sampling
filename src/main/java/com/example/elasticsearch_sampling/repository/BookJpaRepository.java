package com.example.elasticsearch_sampling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.elasticsearch_sampling.domain.Book;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

}
