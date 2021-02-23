package br.com.zup.casadocodigo.controller.dto;

import br.com.zup.casadocodigo.model.Book;

public class BookDto {
    private Long id;

    private String title;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
