package zup.jonas.souza.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zup.jonas.souza.casadocodigo.model.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
}
