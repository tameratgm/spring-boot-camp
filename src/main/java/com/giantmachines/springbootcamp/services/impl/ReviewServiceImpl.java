package com.giantmachines.springbootcamp.services.impl;

import com.giantmachines.springbootcamp.api.requests.CreateReviewRequest;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.models.Review;
import com.giantmachines.springbootcamp.models.User;
import com.giantmachines.springbootcamp.repositories.ReviewRepository;
import com.giantmachines.springbootcamp.services.ReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  @Override
  public Review create(Book book, User user, CreateReviewRequest request) {
    Review review = Review.builder()
            .book(book)
            .writer(user)
            .score(request.getScore())
            .title(request.getTitle())
            .text(request.getText())
            .build();

    return reviewRepository.save(review);
  }
}
