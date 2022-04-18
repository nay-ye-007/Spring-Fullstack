package com.jdc.assignment.models;

import java.util.List;

import com.jdc.assignment.domains.OpenClass;

public interface OpenClassModel {
	
	List<OpenClass> findByCourse(int courseId);
	void save(OpenClass openClass);
	OpenClass findById(int id);
}
