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
		if(request.getPathInfo().equals("/searchform")) {
			String sid = request.getParameter("sid");
			Student student = studentService.searchStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();
			if(student!=null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<table border='1px solid black'>");
				out.println("<tr><th>Name</th><th>age</th><th>Address</th></tr>");
				out.println("<tr><td>"+student.getSname()+"</td><td>"+student.getSage()+"</td><td>"+student.getSaddress()+"</td></tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			}else {
				out.println("<center>");
				out.println("<h2 style='color:green;'>Not Found!! </h1>");
				out.println("</center>");
			}
			out.close();
			
		}
		if(request.getPathInfo().equals("/deleteform")) {
			String sid = request.getParameter("sid");
			String status = studentService.deleteStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();
			if(status.equals("success")) out.println("<h1 style='color:green; text-align:center;'>Deleted Successfully</h1>");
			else out.println("<h1 style='color:red; text-align:center;'>Record Not Found!!</h1>");
		}
		if(request.getPathInfo().equals("/editform")) {
			String sid = request.getParameter("sid");
			Student searchStudent = studentService.searchStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();
			if(searchStudent!=null) {
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./updateRecord'>");
				out.println("<table>"); 
				out.println("<tr><th>ID</th><td>"+searchStudent.getSid()+"</td></tr>");
				System.out.println(searchStudent.getSid());
				out.println("<input type='hidden' name='sid' value='"+searchStudent.getSid()+"'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='"+searchStudent.getSname()+"'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='"+searchStudent.getSage()+"'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='"+searchStudent.getSaddress()+"'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='Search' /></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>"); 
				out.println("</body>");
			}else {
				out.println("<h1 style='color:red; text-align:center;'>Record Not Found!!</h1>");
			}
			out.close();
		}
		if(request.getPathInfo().equals("/updateRecord")) {
			PrintWriter out = response.getWriter();
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");
			Student updateStudent = new Student();
			updateStudent.setSaddress(saddress);
			updateStudent.setSid(Integer.parseInt(sid));
			updateStudent.setSname(sname);
			updateStudent.setSage(Integer.parseInt(sage));
			String status = studentService.updateStudent(updateStudent);
			if(status.equals("success")) out.println("<h1 style='color:green; text-align:center;'>Update Successfully</h1>");
			else out.println("<h1 style='color:red; text-align:center;'>This Shouldn't be happening!!</h1>");
		}
	}

}
