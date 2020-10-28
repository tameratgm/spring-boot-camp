package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.api.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

  Book create(CreateBookRequest bookRequest);

  Optional<Book> get(long id);

  List<Book> getAll();

  Book save(Book book);
}
