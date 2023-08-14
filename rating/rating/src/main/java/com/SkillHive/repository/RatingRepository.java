package com.SkillHive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SkillHive.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{
List<Rating>findRatingByCourseId(Long courseId);
}
