package com.giantmachines.springbootcamp.services.integrations.nyt;

import com.giantmachines.springbootcamp.api.responses.integrations.nyt.NewYorkTimesBestSellerResponse;
import com.giantmachines.springbootcamp.converters.impl.NewYorkTimesBookConverter;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.utils.HttpUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewYorkTimesBookService {

  private final NewYorkTimesBookConverter converter;

  @Value("${nytimes.baseurl}")
  private String baseUrl;

  @Value("${nytimes.key}")
  private String apiKey;

  public NewYorkTimesBookService(NewYorkTimesBookConverter converter) {
    this.converter = converter;
  }

  public List<Book> fetchBestSellers() {
    return HttpUtility.get(createUrl("lists/current/hardcover-fiction.json"), NewYorkTimesBestSellerResponse.class)
            .stream()
            .flatMap(r -> r.getBooks().stream())
            .map(converter::convert)
            .collect(Collectors.toList());
  }

  private String createUrl(String resource) {
    return String.format("%s/%s?api-key=%s", baseUrl, resource, apiKey);
  }

}
