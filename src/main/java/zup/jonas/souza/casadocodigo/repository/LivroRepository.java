package zup.jonas.souza.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import zup.jonas.souza.casadocodigo.model.Livro;

import java.util.List;

public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

}
