package zup.jonas.souza.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zup.jonas.souza.casadocodigo.controller.dto.DetalheLivroDto;
import zup.jonas.souza.casadocodigo.controller.dto.LivroDto;
import zup.jonas.souza.casadocodigo.controller.form.NovoLivroForm;
import zup.jonas.souza.casadocodigo.exception.NotFoundException;
import zup.jonas.souza.casadocodigo.model.Livro;
import zup.jonas.souza.casadocodigo.repository.AutorRepository;
import zup.jonas.souza.casadocodigo.repository.CategoriaRepository;
import zup.jonas.souza.casadocodigo.repository.LivroRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalheLivroDto> save(@RequestBody @Valid NovoLivroForm form, UriComponentsBuilder uriBuilder) {
        var livro = livroRepository.save(form.toModel(autorRepository, categoriaRepository));
        URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalheLivroDto(livro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> find(@PathVariable Long id) {
        var livro = livroRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return ResponseEntity.ok(new LivroDto(livro));
    }

    @GetMapping
    public Page<LivroDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        return livroRepository.findAll(paginacao).map(LivroDto::new);
    }

}
