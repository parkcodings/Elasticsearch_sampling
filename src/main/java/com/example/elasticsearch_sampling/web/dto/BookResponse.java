package com.example.elasticsearch_sampling.web.dto;

public record BookResponse(
        Long id,
        String title,
        String author,
        String description) {

}
