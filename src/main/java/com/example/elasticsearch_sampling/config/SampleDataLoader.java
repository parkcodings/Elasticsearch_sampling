package com.example.elasticsearch_sampling.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.elasticsearch_sampling.service.BookService;
import com.example.elasticsearch_sampling.web.dto.BookRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SampleDataLoader implements CommandLineRunner {

    private final BookService bookService;

    @Override
    public void run(String... args) {
        if (!bookService.findAll().isEmpty()) {
            return;
        }

        bookService.create(new BookRequest("자바의 정석", "남궁성", "자바 기초를 차근차근 설명하는 대표 입문서"));
        bookService.create(new BookRequest("스프링 인 액션", "Craig Walls", "스프링 핵심 개념을 예제로 익히는 책"));
        bookService.create(new BookRequest("토비의 스프링", "이일민", "스프링의 원리와 설계를 깊이 있게 다루는 책"));
        bookService.create(new BookRequest("HTTP 완벽 가이드", "David Gourley", "웹과 HTTP 통신의 기초를 이해하는 데 도움을 주는 책"));
        bookService.create(new BookRequest("클린 코드", "Robert C. Martin", "읽기 좋은 코드와 유지보수하기 좋은 코드에 대한 책"));
    }

}
