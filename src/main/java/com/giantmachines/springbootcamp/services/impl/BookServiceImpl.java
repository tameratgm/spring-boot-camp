package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.services.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

  private List<Book> bookDb = new ArrayList<>();

  @Override
  public Book create(CreateBookRequest bookRequest) {
    long newId = bookDb.isEmpty()
            ? 1
            : bookDb.get(bookDb.size() - 1).getId() + 1;

    Book book = Book
            .builder()
            .id(newId)
            .title(bookRequest.getTitle())
            .build();

    bookDb.add(book);

    return book;
  }

  @Override
  public Optional<Book> get(long id) {
    return bookDb
            .stream()
            .filter(b -> b.getId() == id)
            .findFirst();
  }

  @Override
  public List<Book> getAll() {
    return bookDb;
  }
}
