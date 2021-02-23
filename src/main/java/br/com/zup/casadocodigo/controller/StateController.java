package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.controller.dto.StateDto;
import br.com.zup.casadocodigo.controller.request.NewStateRequest;
import br.com.zup.casadocodigo.repository.CountryRepository;
import br.com.zup.casadocodigo.repository.StateRepository;
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
@RequestMapping("/states")
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<StateDto> save(@RequestBody @Valid NewStateRequest req, UriComponentsBuilder uriBuilder) {
        var state = stateRepository.save(req.toModel(countryRepository));
        URI uri = uriBuilder.path("/states/{id}").buildAndExpand(state.getId()).toUri();
        return ResponseEntity.created(uri).body(new StateDto(state));
    }

    @GetMapping
    public Page<StateDto> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pagination) {
        return stateRepository.findAll(pagination).map(StateDto::new);
    }
}
