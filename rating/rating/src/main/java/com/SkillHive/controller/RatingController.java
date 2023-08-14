package com.SkillHive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SkillHive.entity.Rating;
import com.SkillHive.entity.exception.UserNotFoundException;
import com.SkillHive.entity.exception.CourseNotFoundException;
import com.SkillHive.entity.exception.UserNotFoundException;
import com.SkillHive.entity.service.RatingService;

@Controller
@RequestMapping("/rating")
public class RatingController {
	private final RatingService rs;

    @Autowired
    public RatingController(RatingService rs) {
        this.rs = rs;
    }
    @GetMapping
    public ResponseEntity<List<Rating>>getAllRatings(){
    	List<Rating>ratings=rs.getAllRating();
    return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating){
    	try {
    	Rating rate=rs.create(rating.getCourseId(), rating.getUserId(), rating.getRating());
    	 return new ResponseEntity<>(rating, HttpStatus.CREATED); 	 
    	}catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (CourseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byCourse/{courseId}")
  public ResponseEntity<List<Rating>>getRatingByCourseId(Long courseId){
    	List<Rating>ratings=rs.getRatingByCourseId(courseId);
    	return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
}
