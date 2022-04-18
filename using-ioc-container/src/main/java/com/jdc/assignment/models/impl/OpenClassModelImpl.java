package com.jdc.assignment.models.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domains.Course;
import com.jdc.assignment.domains.OpenClass;
import com.jdc.assignment.models.OpenClassModel;

public class OpenClassModelImpl implements OpenClassModel{
	
	private static final String SELECT_ALL = """
			select oc.id, oc.teacher, oc.start_date, 
			c.id courseId, c.name, c.fees, c.duration, c.description 
			from open_class oc join course c on oc.course_id = c.id where c.id = ?
			""";
	private static final String INSERT = "insert into open_class (course_id, teacher, start_date) values (?, ?, ?)";
	private static final String SELECT_ID = """
			select oc.id, oc.teacher, oc.start_date, 
			c.id courseId, c.name, c.fees, c.duration, c.description 
			from open_class oc join course c on oc.course_id = c.id where oc.id = ?
			""";
	private DataSource dataSource;

	public OpenClassModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<OpenClass> findByCourse(int courseId) {
		
		var list = new ArrayList<OpenClass>();
		
		try(var con = dataSource.getConnection(); var stmt = con.prepareStatement(SELECT_ALL)) {
			
			stmt.setInt(1, courseId);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				var course = new Course();
				course.setId(rs.getInt("courseId"));
				course.setName(rs.getString("name"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));
				course.setDescription(rs.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setId(rs.getInt("id"));
				openClass.setCourse(course);
				openClass.setTeacher(rs.getString("teacher"));
				openClass.setStartDate(rs.getDate("start_date").toLocalDate());
				
				list.add(openClass);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void save(OpenClass openClass) {
		
		try(var con = dataSource.getConnection(); var stmt = con.prepareStatement(INSERT)) {
			
			stmt.setInt(1, openClass.getCourse().getId());
			stmt.setString(2, openClass.getTeacher());
			stmt.setDate(3, Date.valueOf(openClass.getStartDate()));
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OpenClass findById(int id) {
		
		try(var con = dataSource.getConnection(); var stmt = con.prepareStatement(SELECT_ID)) {
			
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				var course = new Course();
				course.setId(rs.getInt("courseId"));
				course.setName(rs.getString("name"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));
				course.setDescription(rs.getString("description"));
				
				var openClass = new OpenClass();
				openClass.setId(rs.getInt("id"));
				openClass.setCourse(course);
				openClass.setTeacher(rs.getString("teacher"));
				openClass.setStartDate(rs.getDate("start_date").toLocalDate());
				
				return openClass;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
