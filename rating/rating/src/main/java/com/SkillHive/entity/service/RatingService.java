package com.SkillHive.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SkillHive.entity.Rating;
import com.SkillHive.repository.RatingRepository;

@Service
public class RatingService {
	private final RatingRepository rr;

    @Autowired
    public RatingService(RatingRepository rr) {
        this.rr = rr;
    }
    //get all ratings 
	public List<Rating> getAllRating() {
		return rr.findAll();
	}
	//get ratings by courseId
	public List<Rating>getRatingByCourseId(Long courseId){
		return rr.findRatingByCourseId(courseId);
	}
	//create Ratings
	public Rating create(Long courseId,Long userId,int rating) {
		Rating rate=new Rating(courseId,userId,rating);
		return rr.save(rate);
	}
	
	
}
