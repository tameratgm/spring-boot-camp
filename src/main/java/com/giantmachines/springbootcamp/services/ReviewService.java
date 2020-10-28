package com.giantmachines.springbootcamp.services;

import com.giantmachines.springbootcamp.api.requests.CreateReviewRequest;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.models.Review;
import com.giantmachines.springbootcamp.models.User;

public interface ReviewService {

  Review create(Book book, User user, CreateReviewRequest request);

}
