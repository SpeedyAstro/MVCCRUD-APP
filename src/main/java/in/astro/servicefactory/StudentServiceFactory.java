package in.astro.servicefactory;

import in.astro.service.IStudentService;
import in.astro.service.StudentServiceImpl;

public class StudentServiceFactory {
	private StudentServiceFactory() {
		
	}
	private static IStudentService studentService = null;
	public static IStudentService getStudentService() {
		if(studentService==null)
			studentService = new StudentServiceImpl();
		return studentService;
	}
}
