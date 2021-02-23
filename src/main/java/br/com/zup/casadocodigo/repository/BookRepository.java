package br.com.zup.casadocodigo.repository;

import br.com.zup.casadocodigo.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}
