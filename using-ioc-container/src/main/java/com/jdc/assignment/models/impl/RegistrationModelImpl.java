package com.jdc.assignment.models.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domains.Course;
import com.jdc.assignment.domains.OpenClass;
import com.jdc.assignment.domains.Registration;
import com.jdc.assignment.models.RegistrationModel;

public class RegistrationModelImpl implements RegistrationModel {

	private static final String SELECT_ALL = """
			select rg.id, rg.student, rg.phone, rg.email, 
			oc.id openClassId, oc.start_date, oc.teacher, 
			c.id courseId, c.name, c.fees, c.duration, c.description
			from registration rg join open_class oc on rg.open_class_id = oc.id join course c on oc.course_id = c.id where oc.id = ?
			""";
	private static final String INSERT = "insert into registration (open_class_id, student, phone, email) values (?, ?, ?, ?)";
	private DataSource dataSource;

	public RegistrationModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Registration> findByClass(int classId) {
		
		var list = new ArrayList<Registration>();
		
		try(var con = dataSource.getConnection(); var stmt = con.prepareStatement(SELECT_ALL)) {
			
			stmt.setInt(1, classId);
			var rs = stmt.executeQuery();
			while(rs.next()) {
				var course = new Course();
				course.setId(rs.getInt("courseId"));
				course.setName(rs.getString("name"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));
				course.setDescription(rs.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setId(rs.getInt("openClassId"));
				openClass.setCourse(course);
				openClass.setTeacher(rs.getString("teacher"));
				openClass.setStartDate(rs.getDate("start_date").toLocalDate());
				
				var registration  = new Registration();
				registration.setId(rs.getInt("id"));
				registration.setOpenClass(openClass);
				registration.setStudent(rs.getString("student"));
				registration.setPhone(rs.getString("phone"));
				registration.setEmail(rs.getString("email"));
				
				list.add(registration);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void save(Registration registration) {
		
		try(var con = dataSource.getConnection(); var stmt = con.prepareStatement(INSERT)) {
			
			stmt.setInt(1, registration.getOpenClass().getId());
			stmt.setString(2, registration.getStudent());
			stmt.setString(3, registration.getPhone());
			stmt.setString(4, registration.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
