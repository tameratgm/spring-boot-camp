package com.giantmachines.springbootcamp.services.integrations.nyt;

import com.giantmachines.springbootcamp.api.responses.integrations.nyt.NewYorkTimesBestSellerResponse;
import com.giantmachines.springbootcamp.converters.impl.NewYorkTimesBookConverter;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.services.BookService;
import com.giantmachines.springbootcamp.utils.HttpUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewYorkTimesBookService {

  private final BookService bookService;

  private final NewYorkTimesBookConverter converter;

  @Value("${nytimes.baseurl}")
  private String baseUrl;

  @Value("${nytimes.key}")
  private String apiKey;

  public NewYorkTimesBookService(BookService bookService,
                                 NewYorkTimesBookConverter converter) {
    this.bookService = bookService;
    this.converter = converter;
  }

  public List<Book> fetchBestSellers() {
    return HttpUtility.get(createUrl("lists/current/hardcover-fiction.json"), NewYorkTimesBestSellerResponse.class)
            .stream()
            .flatMap(r -> r.getBooks().stream())
            .map(converter::convert)
            .map(bookService::save)
            .collect(Collectors.toList());
  }

  private String createUrl(String path) {
    return baseUrl + "/" + path + "?" + "api-key=" + apiKey;
  }

  // Normally, this is considered bad practice
  // but it's OK in this instance since we're using this to load all the books in the DB on startup.
  @PostConstruct
  private void init() {
    System.err.println("Fetching best sellers list from NY Times");
    List<Book> books = fetchBestSellers();
    System.err.println(books.size() + " books were saved into the DB.");
  }
}
