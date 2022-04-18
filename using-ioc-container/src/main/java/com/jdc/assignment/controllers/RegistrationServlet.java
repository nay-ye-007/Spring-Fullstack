package com.jdc.assignment.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdc.assignment.domains.Registration;
import com.jdc.assignment.models.OpenClassModel;
import com.jdc.assignment.models.RegistrationModel;

@WebServlet(urlPatterns = {"/registration", "/registration-edit"})
public class RegistrationServlet extends AbstractBeanFactoryServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var openClass = getBean("openClassModel", OpenClassModel.class).findById(Integer.parseInt(req.getParameter("classId")));
		req.setAttribute("openClass", openClass);
		
		var page = switch(req.getServletPath()) {
		case "/registration" -> {
			var registration = getBean("registrationModel", RegistrationModel.class).findByClass(Integer.parseInt(req.getParameter("classId")));
			req.setAttribute("registration", registration);
			yield "registration";
		}
		default -> "registration-edit";
		};
		
		getServletContext().getRequestDispatcher("/%s.jsp".formatted(page)).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var student = req.getParameter("student");
		var phone = req.getParameter("phone");
		var email = req.getParameter("email");
		var classId = req.getParameter("classId");
		var openClass = getBean("openClassModel", OpenClassModel.class).findById(Integer.parseInt(classId));
		var registration = new Registration();
		registration.setOpenClass(openClass);
		registration.setStudent(student);
		registration.setPhone(phone);
		registration.setEmail(email);
		
		getBean("registrationModel", RegistrationModel.class).save(registration);
		
		resp.sendRedirect("/registration?classId=" + classId);
	}

}
