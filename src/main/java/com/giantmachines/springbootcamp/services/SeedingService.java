package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.models.Book;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeedingService {

  private final BookService bookService;

  public SeedingService(BookService bookService) {
    this.bookService = bookService;
  }

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedNewYorkTimesBestSellers();
  }

  private void seedNewYorkTimesBestSellers() {
    System.err.println("Fetching best sellers list from NY Times");
    List<Book> books = bookService.fetchBestSellers();
    System.err.println(books.size() + " books fetched and saved.");
  }

}
