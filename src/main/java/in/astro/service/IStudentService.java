package in.astro.service;

import in.astro.dto.Student;

public interface IStudentService {
	// operations to be implemented
		public String addStudent(Student student);
		
		public Student searchStudent(Integer sid);
				
		public String deleteStudent(Integer sid);

		public String updateStudent(Student student);
}
