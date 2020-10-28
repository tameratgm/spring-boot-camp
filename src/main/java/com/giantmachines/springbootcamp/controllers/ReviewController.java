package com.giantmachines.springbootcamp.controllers;

import com.giantmachines.springbootcamp.api.requests.CreateReviewRequest;
import com.giantmachines.springbootcamp.models.Book;
import com.giantmachines.springbootcamp.models.Review;
import com.giantmachines.springbootcamp.services.BookService;
import com.giantmachines.springbootcamp.services.ReviewService;
import com.giantmachines.springbootcamp.services.UserService;
import com.giantmachines.springbootcamp.utils.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("books/{bookId}/reviews")
public class ReviewController {

  private final BookService bookService;
  private final UserService userService;
  private final ReviewService reviewService;

  public ReviewController(BookService bookService,
                          UserService userService,
                          ReviewService reviewService) {
    this.bookService = bookService;
    this.userService = userService;
    this.reviewService = reviewService;
  }

  @PostMapping
  public ResponseEntity<Review> create(@PathVariable long bookId, @Valid @RequestBody CreateReviewRequest request) {

    Review review = bookService.get(bookId)
            .flatMap(book -> userService.get(request.getUserId())
                    .map(user -> reviewService.create(book, user, request)))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "unable to create review"));

    return ResponseFactory.created(review);
  }

  @GetMapping
  public ResponseEntity<List<Review>> getAll(@PathVariable long bookId) {

    List<Review> reviews = bookService.get(bookId)
            .map(Book::getReviews)
            .orElse(List.of());

    return ResponseFactory.ok(reviews);
  }

}
