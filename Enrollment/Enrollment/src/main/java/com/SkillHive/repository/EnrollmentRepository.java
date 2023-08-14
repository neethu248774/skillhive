package com.SkillHive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SkillHive.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
	List<Enrollment>findByUserId(Long userId);
	List<Enrollment>findByCourseId(Long courseId);
	@Query("from Enrollment e where e.userId=:userId and e.courseId=:courseId")
	List<Enrollment>findByUserandCourseId(@Param("userId") Long userId,@Param("courseId") Long courseId);
	boolean existsByCourseIdAndUserId(Long courseId, Long UserId);
	
}
