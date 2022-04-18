package com.jdc.assignment.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domains.OpenClass;
import com.jdc.assignment.models.CourseModel;
import com.jdc.assignment.models.OpenClassModel;

@WebServlet(urlPatterns = {"/classes", "/class-edit"})
public class OpenClassServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var courseId = req.getParameter("courseId");
		var course = getBean("courseModel", CourseModel.class).findById(Integer.parseInt(courseId));
		req.setAttribute("course", course);
		
		var page = switch(req.getServletPath()) {
		case  "/classes" -> {
			var openClasses = getBean("openClassModel", OpenClassModel.class).findByCourse(Integer.parseInt(courseId));
			req.setAttribute("classes", openClasses);
			yield "classes";
		}
		default -> "class-edit";
		};
		
		getServletContext().getRequestDispatcher("/%s.jsp".formatted(page)).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var courseId = req.getParameter("courseId");
		var teacher = req.getParameter("teacherName");
		var startDate = req.getParameter("startDate");
		var course = getBean("courseModel", CourseModel.class).findById(Integer.parseInt(courseId));
		var openClass = new OpenClass();
		openClass.setCourse(course);
		openClass.setTeacher(teacher);
		openClass.setStartDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		getBean("openClassModel", OpenClassModel.class).save(openClass);
		
		resp.sendRedirect("/classes?courseId=" + courseId);
	}
}
