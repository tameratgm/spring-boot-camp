package com.giantmachines.springbootcamp.repositories;

import com.giantmachines.springbootcamp.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
  
}
