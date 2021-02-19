package zup.jonas.souza.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zup.jonas.souza.casadocodigo.controller.dto.AutorDto;
import zup.jonas.souza.casadocodigo.controller.form.NovoAutorForm;
import zup.jonas.souza.casadocodigo.exception.NotFoundException;
import zup.jonas.souza.casadocodigo.repository.AutorRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> save(@RequestBody @Valid NovoAutorForm form, UriComponentsBuilder uriBuilder) {
        var author = autorRepository.save(form.toModel());
        URI uri = uriBuilder.path("/authors/{id}").buildAndExpand(author.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDto(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> find(@PathVariable Long id) {
        var author = autorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.ok(new AutorDto(author));
    }

}
