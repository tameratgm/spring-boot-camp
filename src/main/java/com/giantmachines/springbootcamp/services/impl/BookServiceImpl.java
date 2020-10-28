package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.api.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.repositories.BookRepository;
import com.giantmachines.springbootcamp.services.BookService;
import com.giantmachines.springbootcamp.services.UserService;
import com.giantmachines.springbootcamp.utils.CollectionFactory;
import org.springframework.stereotype.Service;

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
  
}
