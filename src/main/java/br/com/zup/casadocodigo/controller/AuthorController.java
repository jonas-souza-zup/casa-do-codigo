package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.controller.dto.AuthorDto;
import br.com.zup.casadocodigo.controller.request.NewAuthorRequest;
import br.com.zup.casadocodigo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zup.casadocodigo.exception.NotFoundException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<AuthorDto> save(@RequestBody @Valid NewAuthorRequest req, UriComponentsBuilder uriBuilder) {
        var author = authorRepository.save(req.toModel());
        URI uri = uriBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(uri).body(new AuthorDto(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> find(@PathVariable Long id) {
        var author = authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.ok(new AuthorDto(author));
    }

}
