package com.jdc.assignment.models.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.jdc.assignment.domains.Course;
import com.jdc.assignment.models.CourseModel;

public class CourseModelImpl implements CourseModel {

	private static final String SELECT = "select * from course";
	private static final String INSERT = "insert into course (name, fees, duration, description) values (?, ?, ?, ?)";
	private static final String SELECT_ID = "select * from course where id = ?";
	private DataSource dataSource;

	public CourseModelImpl(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public List<Course> findAll() {

		var list = new ArrayList<Course>();

		try (var con = dataSource.getConnection(); var stmt = con.prepareStatement(SELECT)) {
			
			var rs = stmt.executeQuery();
			while(rs.next()) {
				
				var course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setDescription(rs.getString("description"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));
				
				list.add(course);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void save(Course course) {
		
		try(var con = dataSource.getConnection(); var stmt = con.prepareStatement(INSERT)) {
			
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getFees());
			stmt.setInt(3, course.getDuration());
			stmt.setString(4, course.getDescription());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Course findById(int id) {
		
		try(var con = dataSource.getConnection(); var stmt= con.prepareStatement(SELECT_ID)) {
			
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				var course = new Course();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));
				course.setDescription(rs.getString("description"));
				
				return course;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

}
