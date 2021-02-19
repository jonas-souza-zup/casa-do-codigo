package zup.jonas.souza.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zup.jonas.souza.casadocodigo.model.Livro;

public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {
}
