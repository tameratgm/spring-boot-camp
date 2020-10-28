package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.enums.BookOperation;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.models.User;

public interface LibraryService {

  Book updateAvailability(Book book, User user, BookOperation operation);
  
}
