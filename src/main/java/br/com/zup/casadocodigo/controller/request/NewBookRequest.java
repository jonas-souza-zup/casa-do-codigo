package br.com.zup.casadocodigo.controller.request;

import br.com.zup.casadocodigo.exception.NotFoundException;
import br.com.zup.casadocodigo.model.Author;
import br.com.zup.casadocodigo.model.Book;
import br.com.zup.casadocodigo.model.Category;
import br.com.zup.casadocodigo.repository.AuthorRepository;
import br.com.zup.casadocodigo.repository.CategoryRepository;
import br.com.zup.casadocodigo.validation.annotation.Exists;
import br.com.zup.casadocodigo.validation.annotation.Unique;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {

    @NotBlank
    @Unique(modelClass = Book.class, field = "title")
    private String title;

    @NotBlank
    @Size(max = 500)
    private String resume;

    private String summary;

    @NotNull
    @DecimalMin("20")
    private BigDecimal price;

    @NotNull
    @Min(100)
    private Integer pageCount;

    @NotBlank
    @Unique(modelClass = Book.class, field = "isbn")
    private String isbn;

    @Future
    private LocalDate publishDate;

    @NotNull
    @Exists(modelClass = Category.class, field = "id")
    private Long categoryId;

    @NotNull
    @Exists(modelClass = Author.class, field = "id")
    private Long authorId;

    public NewBookRequest(@NotBlank String title, @NotBlank @Size(max = 500) String resume, String summary, @NotNull @NotEmpty @Min(20) BigDecimal price, @NotNull @Min(100) Integer pageCount, @NotBlank String isbn, @Future LocalDate publishDate, @NotNull Long categoryId, @NotNull Long authorId) {
        this.title = title;
        this.resume = resume;
        this.summary = summary;
        this.price = price;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getResume() {
        return resume;
    }

    public String getSummary() {
        return summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Book toModel(AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        var autor = authorRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
        var categoria = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException(categoryId));
        return new Book(title, resume, summary, price, pageCount, isbn, publishDate, categoria, autor);
    }
}