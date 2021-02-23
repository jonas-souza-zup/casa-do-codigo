package br.com.zup.casadocodigo.controller.dto;

import br.com.zup.casadocodigo.model.Author;

import java.time.LocalDate;

public class AuthorDto {
    private Long id;

    private String name;

    private String email;

    private String description;

    private LocalDate createdAt;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.email = author.getEmail();
        this.description = author.getDescription();
        this.createdAt = author.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

}
