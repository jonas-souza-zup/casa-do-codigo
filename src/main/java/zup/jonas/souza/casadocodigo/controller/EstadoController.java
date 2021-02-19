package zup.jonas.souza.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zup.jonas.souza.casadocodigo.controller.dto.EstadoDto;
import zup.jonas.souza.casadocodigo.controller.form.NovoEstadoForm;
import zup.jonas.souza.casadocodigo.repository.EstadoRepository;
import zup.jonas.souza.casadocodigo.repository.PaisRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoDto> save(@RequestBody @Valid NovoEstadoForm form, UriComponentsBuilder uriBuilder) {
        var estado = estadoRepository.save(form.toModel(paisRepository));
        URI uri = uriBuilder.path("/estados/{id}").buildAndExpand(estado.getId()).toUri();
        return ResponseEntity.created(uri).body(new EstadoDto(estado));
    }

    @GetMapping
    public Page<EstadoDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        return estadoRepository.findAll(paginacao).map(EstadoDto::new);
    }
}
