package zup.jonas.souza.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import zup.jonas.souza.casadocodigo.controller.dto.ClienteDto;
import zup.jonas.souza.casadocodigo.controller.form.NovoClienteForm;
import zup.jonas.souza.casadocodigo.repository.ClienteRepository;
import zup.jonas.souza.casadocodigo.repository.EstadoRepository;
import zup.jonas.souza.casadocodigo.repository.PaisRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> save(@RequestBody @Valid NovoClienteForm form, UriComponentsBuilder uriBuilder) {
        var cliente = clienteRepository.save(form.toModel(paisRepository, estadoRepository));
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(cliente));
    }

    @GetMapping
    public Page<ClienteDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable paginacao) {
        return clienteRepository.findAll(paginacao).map(ClienteDto::new);
    }

}
