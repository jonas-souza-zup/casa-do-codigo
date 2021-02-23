package br.com.zup.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.zup.casadocodigo.model.State;

public interface StateRepository extends PagingAndSortingRepository<State, Long> {
}
