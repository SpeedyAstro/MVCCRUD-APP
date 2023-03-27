package in.astro.daofactory;

import in.astro.dao.IStudentDao;
import in.astro.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private StudentDaoFactory() {
		
	}
	private static IStudentDao studentDao = null;
	
	public static IStudentDao getStudentDao() {
		if(studentDao==null)
			studentDao = new StudentDaoImpl();
		return studentDao;
	}
}