package com.giantmachines.springbootcamp.converters.impl;

import com.giantmachines.springbootcamp.api.responses.integrations.nyt.NewYorkTimesBook;
import com.giantmachines.springbootcamp.converters.Converter;
import com.giantmachines.springbootcamp.models.Book;
import org.springframework.stereotype.Component;

@Component
public class NewYorkTimesBookConverter implements Converter<NewYorkTimesBook, Book> {

  @Override
  public Book convert(NewYorkTimesBook input) {
    return Book.builder()
            .title(input.getTitle())
            .build();
  }

}
