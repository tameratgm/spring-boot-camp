package com.giantmachines.springbootcamp.controllers;

import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.utils.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

  private List<Book> bookDb = new ArrayList<>();

  @PostMapping
  public ResponseEntity<Book> create(@Valid @RequestBody CreateBookRequest createBookRequest) {
    long newId = bookDb.isEmpty()
            ? 1
            : bookDb.get(bookDb.size() - 1).getId() + 1;

    Book book = Book
            .builder()
            .id(newId)
            .title(createBookRequest.getTitle())
            .build();

    bookDb.add(book);

    return ResponseFactory.created(book);
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAll() {
    return ResponseFactory.ok(bookDb);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getById(@PathVariable long id) {
    Optional<Book> book = bookDb
            .stream()
            .filter(b -> b.getId() == id)
            .findFirst();

    return ResponseFactory.ok(book);
  }

}
