package com.jdc.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domains.Course;
import com.jdc.assignment.models.CourseModel;

@WebServlet(urlPatterns = { "/", "/courses" })
public class CourseServlet extends AbstractBeanFactoryServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var page = switch (req.getServletPath()) {
		case "/course-edit" -> "/course-edit.jsp";
		default -> {
			var courses = getBean("courseModel", CourseModel.class).findAll();
			req.setAttribute("courses", courses);
			yield "/index.jsp";
		}
		};
		
		getServletContext().getRequestDispatcher(page).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var name = req.getParameter("name");
		var fees = req.getParameter("fees");
		var duration = req.getParameter("duration");
		var description = req.getParameter("description");
		
		var course = new Course();
		course.setName(name);
		course.setFees(Integer.parseInt(fees));
		course.setDuration(Integer.parseInt(duration));
		course.setDescription(description);
		
		getBean("courseModel", CourseModel.class).save(course);
		
		resp.sendRedirect("/");
	}
}
