package in.astro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.astro.dto.Student;
import in.astro.service.IStudentService;
import in.astro.servicefactory.StudentServiceFactory;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Student newstudent = new Student();
		IStudentService studentService = StudentServiceFactory.getStudentService();
		System.out.println("Request URI ::" + request.getRequestURI());
		System.out.println("Path info :: "+request.getPathInfo());
		if(request.getPathInfo().equals("/addform")) {
			String name = request.getParameter("sname");
			String age = request.getParameter("sage");
			String address = request.getParameter("saddr");
			newstudent.setSname(name);
			newstudent.setSage(Integer.parseInt(age));
			newstudent.setSaddress(address);
			String status = studentService.addStudent(newstudent);
			PrintWriter out = response.getWriter();
			if(status.equalsIgnoreCase("success")) {
				out.println("<h1 style='color:green; text-align:center;'>Registration Successfull</h1>");
			}else {
				out.println("<h1 style='color:red; text-align:center;'>Registration Failed</h1>");

			}
		}
	}

}
