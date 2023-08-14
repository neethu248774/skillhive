package com.SkillHive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.SkillHive.entity.Enrollment;
import com.SkillHive.exception.CourseNotFoundException;
import com.SkillHive.exception.EnrollmentExistsException;
import com.SkillHive.exception.UserNotFoundException;
import com.SkillHive.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
	
private final EnrollmentRepository enrollmentRepository ;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        super();
        this.enrollmentRepository = enrollmentRepository;
    }
    //get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }
    //get enrollment by enrollmentid
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }
    public List<Enrollment> getEnrollmentsByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }
    public List<Enrollment> getEnrollmentsByCourseIdAndUserId(Long courseId, Long userId) {
        return enrollmentRepository.findByUserandCourseId(userId, courseId);
    }
    public Enrollment createEnrollment(Long courseId, Long userId) {
        if (enrollmentRepository.existsByCourseIdAndUserId(courseId, userId)) {
            throw new EnrollmentExistsException("Enrollment for Course ID " + courseId + " and User ID " + userId + " already exists.");
        }

        // You may want to add additional checks for the existence of the Course and User entities here
        // For simplicity, we'll assume they exist for now.

        Enrollment enrollment = new Enrollment(courseId, userId);
        return enrollmentRepository.save(enrollment);
    }
    public void deleteEnrollmentById(Long id) {
        enrollmentRepository.deleteById(id);
    }
    
}
