package com.example.elasticsearch_sampling.service;

import com.example.elasticsearch_sampling.domain.Book;
import com.example.elasticsearch_sampling.domain.BookDocument;
import com.example.elasticsearch_sampling.repository.BookJpaRepository;
import com.example.elasticsearch_sampling.repository.BookSearchRepository;
import com.example.elasticsearch_sampling.web.dto.BookRequest;
import com.example.elasticsearch_sampling.web.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookJpaRepository bookJpaRepository;
    private final BookSearchRepository bookSearchRepository;

    // 저장
    @Transactional
    public BookResponse create(BookRequest request) {
        Book savedBook = bookJpaRepository.save(
                new Book(request.title(), request.author(), request.description()));

        BookDocument document = new BookDocument(
                savedBook.getId().toString(),
                savedBook.getTitle(),
                savedBook.getAuthor(),
                savedBook.getDescription());

        bookSearchRepository.save(document);

        return toResponse(savedBook);
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<BookResponse> findAll() {
        return bookJpaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 검색
    @Transactional(readOnly = true)
    public List<BookResponse> search(String keyword) {
        return bookSearchRepository.findByTitleContainingOrAuthorContaining(keyword, keyword)
                .stream()
                .map(document -> new BookResponse(
                        Long.valueOf(document.getId()),
                        document.getTitle(),
                        document.getAuthor(),
                        document.getDescription()))
                .toList();
    }

    // 전체 삭제
    @Transactional
    public void deleteAll() {
        bookSearchRepository.deleteAll();
        bookJpaRepository.deleteAll();
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription());
    }
}