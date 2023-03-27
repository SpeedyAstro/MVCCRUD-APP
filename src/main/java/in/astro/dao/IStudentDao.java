package in.astro.dao;

import in.astro.dto.Student;

public interface IStudentDao {
	// operations to be implemented
	public String addStudent(String sname,Integer sage,String saddress);
	
	public Student searchStudent(Integer sid);
		
	public String deleteStudent(Integer sid);

	String updateStudent(Student student);
}
