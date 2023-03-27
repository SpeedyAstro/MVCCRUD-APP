package in.astro.service;

import in.astro.daofactory.StudentDaoFactory;
import in.astro.dto.Student;
import in.astro.dao.IStudentDao;

public class StudentServiceImpl implements IStudentService {
	IStudentDao studentDao;
	@Override
	public String addStudent(Student newstudent) {
		studentDao = StudentDaoFactory.getStudentDao();
//		System.out.println(newstudent.getSname()+";"+newstudent.getSage()+";"+newstudent.getSaddress());
		if(studentDao!=null) return studentDao.addStudent(newstudent.getSname(),newstudent.getSage(),newstudent.getSaddress());
		
		else return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.searchStudent(sid);
	}

	@Override
	public String deleteStudent(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteStudent(sid);
	}

	@Override
	public String updateStudent(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateStudent(student);
	}

}
