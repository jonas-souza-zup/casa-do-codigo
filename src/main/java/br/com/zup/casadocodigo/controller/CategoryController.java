package br.com.zup.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zup.casadocodigo.controller.dto.CategoryDto;
import br.com.zup.casadocodigo.controller.request.NewCategoryRequest;
import br.com.zup.casadocodigo.exception.NotFoundException;
import br.com.zup.casadocodigo.repository.CategoryRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid NewCategoryRequest req, UriComponentsBuilder uriBuilder) {
        var category = categoryRepository.save(req.toModel());
        URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoryDto(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> find(@PathVariable Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.ok(new CategoryDto(category));
    }
}
