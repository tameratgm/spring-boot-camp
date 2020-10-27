package com.giantmachines.springbootcamp.controllers;

import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.requests.CreateBookRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

  private List<Book> bookDb = new ArrayList<>();

  @PostMapping
  public Book create(@Valid @RequestBody CreateBookRequest createBookRequest) {
    long newId = bookDb.isEmpty()
            ? 1
            : bookDb.get(bookDb.size() - 1).getId() + 1;

    Book book = Book
            .builder()
            .id(newId)
            .title(createBookRequest.getTitle())
            .build();

    bookDb.add(book);

    return book;
  }

  @GetMapping
  public List<Book> getAll() {
    return bookDb;
  }

  @GetMapping("/{id}")
  public Book getById(@PathVariable long id) {
    return bookDb
            .stream()
            .filter(book -> book.getId() == id)
            .findFirst()
            .orElseThrow();
  }

}
