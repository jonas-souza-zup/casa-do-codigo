package br.com.zup.casadocodigo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import br.com.zup.casadocodigo.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
