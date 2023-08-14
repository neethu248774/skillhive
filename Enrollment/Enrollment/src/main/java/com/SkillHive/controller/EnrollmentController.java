package com.SkillHive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SkillHive.entity.Enrollment;
import com.SkillHive.exception.CourseNotFoundException;
import com.SkillHive.exception.EnrollmentExistsException;
import com.SkillHive.exception.UserNotFoundException;
import com.SkillHive.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	
	private final EnrollmentService enrollmentService ;
	
	 @Autowired
	    public EnrollmentController(EnrollmentService enrollmentService) {
	        this.enrollmentService = enrollmentService;
	    }
	 @GetMapping
	    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
	        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
	        return new ResponseEntity<>(enrollments, HttpStatus.OK);
	    }

	    @GetMapping("/{enrollmentId}")
	    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {
	        Enrollment enrollment = enrollmentService.getEnrollmentById(enrollmentId);
	        if (enrollment != null) {
	            return new ResponseEntity<>(enrollment, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    @PostMapping
	    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollmentRequest) {
	        try {
	           Enrollment enrollment = enrollmentService.createEnrollment(enrollmentRequest.getCourseId(), enrollmentRequest.getUserId());
	            return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
	        } catch (EnrollmentExistsException e) {
	            return new ResponseEntity<>(HttpStatus.CONFLICT);
	        } catch (UserNotFoundException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } catch (CourseNotFoundException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEnrollmentById(@PathVariable("id") Long id) {
	        enrollmentService.deleteEnrollmentById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    @GetMapping("/byCourse/{courseId}")
	    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
	        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseId(courseId);
	        return new ResponseEntity<>(enrollments, HttpStatus.OK);
	    }

	    @GetMapping("/byUser/{userId}")
	    public ResponseEntity<List<Enrollment>> getEnrollmentsByUserId(@PathVariable Long userId) {
	        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUserId(userId);
	        return new ResponseEntity<>(enrollments, HttpStatus.OK);
	    }

	    @GetMapping("/byCourseAndUser/{courseId}/{userId}")
	    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourseIdAndUserId(@PathVariable Long courseId, @PathVariable Long userId) {
	        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourseIdAndUserId(courseId, userId);
	        return new ResponseEntity<>(enrollments, HttpStatus.OK);
	    }
	}







