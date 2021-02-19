package zup.jonas.souza.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zup.jonas.souza.casadocodigo.controller.dto.CategoriaDto;
import zup.jonas.souza.casadocodigo.controller.form.NovaCategoriaForm;
import zup.jonas.souza.casadocodigo.exception.NotFoundException;
import zup.jonas.souza.casadocodigo.repository.CategoriaRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> save(@RequestBody @Valid NovaCategoriaForm form, UriComponentsBuilder uriBuilder) {
        var category = categoriaRepository.save(form.toModel());
        URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> find(@PathVariable Long id) {
        var category = categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.ok(new CategoriaDto(category));
    }
}
