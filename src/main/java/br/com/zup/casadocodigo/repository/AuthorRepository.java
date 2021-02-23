package br.com.zup.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.zup.casadocodigo.model.Author;

import java.util.List;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> findByEmail(String email);
}
