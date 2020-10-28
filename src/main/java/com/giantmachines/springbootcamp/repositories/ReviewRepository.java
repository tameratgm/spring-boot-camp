package com.giantmachines.springbootcamp.repositories;

import com.giantmachines.springbootcamp.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
