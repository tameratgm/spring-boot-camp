package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.api.requests.CreateBookRequest;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.repositories.BookRepository;
import com.giantmachines.springbootcamp.services.BookService;
import com.giantmachines.springbootcamp.services.integrations.nyt.NewYorkTimesBookService;
import com.giantmachines.springbootcamp.utils.CollectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  private final NewYorkTimesBookService newYorkTimesBookService;

  public BookServiceImpl(BookRepository bookRepository,
                         NewYorkTimesBookService newYorkTimesBookService) {
    this.bookRepository = bookRepository;
    this.newYorkTimesBookService = newYorkTimesBookService;
  }

  @Override
  public Book create(CreateBookRequest bookRequest) {
    Book book = Book
            .builder()
            .title(bookRequest.getTitle())
            .build();

    return save(book);
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
  public List<Book> fetchBestSellers() {
    return newYorkTimesBookService.fetchBestSellers()
            .stream()
            .filter(book -> bookRepository.findByTitle(book.getTitle()).isEmpty())
            .map(this::save)
            .collect(Collectors.toList());
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }

}
