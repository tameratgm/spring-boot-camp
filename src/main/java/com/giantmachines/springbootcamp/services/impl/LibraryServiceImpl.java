package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.enums.BookOperation;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.services.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

  @Override
  public Book updateAvailability(Book book, User user, BookOperation operation) {
    switch (operation) {
      case CHECK_IN:
        return checkIn(book);
      case CHECK_OUT:
        return checkOut(book, user);
      default:
        return book;
    }
  }

  private Book checkIn(Book book) {
    if (!book.isCheckedOut()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("'%s' is not checked out", book.getTitle()));
    }

    book.setOwner(null);

    return book;
  }

  private Book checkOut(Book book, User user) {
    if (book.isCheckedOut()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("'%s' is already checked out", book.getTitle()));
    }

    book.setOwner(user);

    return book;
  }
}
