package com.SkillHive.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long courseId;
	private Long userId;
//	private Date enrollmentDate;

	public Enrollment() {
		// TODO Auto-generated constructor stub
	}

	public Enrollment(Long userId, Long courseId) {
		super();
	
		this.courseId = courseId;
		this.userId = userId;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
