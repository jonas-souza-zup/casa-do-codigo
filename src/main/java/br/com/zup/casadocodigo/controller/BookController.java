package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.controller.dto.BookDetailDto;
import br.com.zup.casadocodigo.controller.dto.BookDto;
import br.com.zup.casadocodigo.controller.request.NewBookRequest;
import br.com.zup.casadocodigo.exception.NotFoundException;
import br.com.zup.casadocodigo.repository.AuthorRepository;
import br.com.zup.casadocodigo.repository.BookRepository;
import br.com.zup.casadocodigo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<BookDetailDto> save(@RequestBody @Valid NewBookRequest req, UriComponentsBuilder uriBuilder) {
        var book = bookRepository.save(req.toModel(authorRepository, categoryRepository));
        URI uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri();
        return ResponseEntity.created(uri).body(new BookDetailDto(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> find(@PathVariable Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.ok(new BookDto(book));
    }

    @GetMapping
    public Page<BookDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pagination) {
        return bookRepository.findAll(pagination).map(BookDto::new);
    }

}
