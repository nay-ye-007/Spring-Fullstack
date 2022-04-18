package com.jdc.assignment.models;

import java.util.List;

import com.jdc.assignment.domains.Registration;

public interface RegistrationModel {
	
	List<Registration> findByClass(int classId);
	void save(Registration registration);
}
