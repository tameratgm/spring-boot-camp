package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.api.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.api.requests.PatchBookRequest;
import com.giantmachines.springbootcamp.enums.BookOperation;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.repositories.BookRepository;
import com.giantmachines.springbootcamp.services.BookService;
import com.giantmachines.springbootcamp.services.UserService;
import com.giantmachines.springbootcamp.utils.CollectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final UserService userService;

  public BookServiceImpl(BookRepository bookRepository,
                         UserService userService) {
    this.bookRepository = bookRepository;
    this.userService = userService;
  }

  @Override
  public Book create(CreateBookRequest bookRequest) {
    Book book = Book
            .builder()
            .title(bookRequest.getTitle())
            .build();

    return bookRepository.save(book);
  }

  @Override
  public Optional<Book> get(long id) {
    return bookRepository.findById(id);
  }

  @Override
  public List<Book> getAll() {
    return CollectionFactory.toList(bookRepository.findAll());
  }

  @Override
  public Optional<Book> updateAvailability(long id, PatchBookRequest request) {
    return get(id)
            .flatMap(book -> userService.get(request.getUserId())
                    .map(user -> updateAvailability(book, user, request.getOperation())));
  }

  private Book updateAvailability(Book book, User user, BookOperation operation) {
    switch (operation) {
      case CHECK_IN:
        if (!book.isCheckedOut()) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("'%s' is not checked out", book.getTitle()));
        }
        book.setOwner(null);
        break;
      case CHECK_OUT:
        if (book.isCheckedOut()) {
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("'%s' is already checked out", book.getTitle()));
        }
        book.setOwner(user);
        break;
      default: // shouldn't happen but it's here to satisfy the compiler
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unknown book operation");
    }

    return book;
  }
}
