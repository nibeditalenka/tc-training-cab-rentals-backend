package com.tc.training.cabrentals.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.tc.training.cabrentals.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID>, QuerydslPredicateExecutor<Review> {
}
