package zup.jonas.souza.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zup.jonas.souza.casadocodigo.controller.dto.PaisDto;
import zup.jonas.souza.casadocodigo.controller.form.NovoPaisForm;
import zup.jonas.souza.casadocodigo.repository.PaisRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PaisDto> save(@RequestBody @Valid NovoPaisForm form, UriComponentsBuilder uriBuilder) {
        var pais = paisRepository.save(form.toModel());
        URI uri = uriBuilder.path("/paises/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).body(new PaisDto(pais));
    }

    @GetMapping
    public Page<PaisDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        return paisRepository.findAll(paginacao).map(PaisDto::new);
    }

}
