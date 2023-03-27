package in.astro.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.astro.dto.Student;
import in.astro.util.JdbcUtil;

public class StudentDaoImpl implements IStudentDao {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultset = null;
	Student student = null;
	
	@Override
	public String addStudent(String sname, Integer sage, String saddress) {
		String query = "insert into students(`name`,`age`,`address`) values (?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(query);
				if(statement!=null) {
					statement.setString(1, sname);
					statement.setInt(2, sage);
					statement.setString(3,saddress);
					int rowaffected = statement.executeUpdate();
					if(rowaffected == 1) return "success";
				}
			}
		}catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteStudent(Integer sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}
