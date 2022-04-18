package com.jdc.assignment.models;

import java.util.List;

import com.jdc.assignment.domains.Course;

public interface CourseModel {
	
	List<Course> findAll();
	void save(Course course);
	Course findById(int id);
}
