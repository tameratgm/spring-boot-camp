package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.requests.CreateBookRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {

  Book create(CreateBookRequest bookRequest);

  Optional<Book> get(long id);

  List<Book> getAll();

}
