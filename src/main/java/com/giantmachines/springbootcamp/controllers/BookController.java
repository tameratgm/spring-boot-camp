package com.giantmachines.springbootcamp.controllers;

import com.giantmachines.springbootcamp.api.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.services.BookService;
import com.giantmachines.springbootcamp.utils.ResponseFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookController {

  private BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @PostMapping
  public ResponseEntity<Book> create(@Valid @RequestBody CreateBookRequest createBookRequest) {

    Book book = bookService.create(createBookRequest);

    return ResponseFactory.created(book);
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAll() {
    List<Book> books = bookService.getAll();

    return ResponseFactory.ok(books);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getById(@PathVariable long id) {

    Optional<Book> book = bookService.get(id);

    return ResponseFactory.ok(book);
  }

}
