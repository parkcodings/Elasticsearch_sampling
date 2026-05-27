package com.example.elasticsearch_sampling.web.dto;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank(message = "제목은 비어 있을 수 없습니다.") String title,
        @NotBlank(message = "저자는 비어 있을 수 없습니다.") String author,
        @NotBlank(message = "설명은 비어 있을 수 없습니다.") String description) {
}
