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
		connection = null;
		statement = null;
		Student student = new Student();
		String query = "select name,age,address from students where id=?";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(query);
				if(statement!=null) {
					statement.setInt(1, sid);
					resultset = statement.executeQuery();
					if(resultset!=null) {
						if(resultset.next()) {
							student.setSid(sid);
							student.setSname(resultset.getString(1));
							student.setSage(resultset.getInt(2));
							student.setSaddress(resultset.getString(3));
							System.out.print(student);
							return student;
						}
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String deleteStudent(Integer sid) {
		String query = "delete from students where id=?";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(query);
				if(statement!=null) {
					statement.setInt(1, sid);
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
	public String updateStudent(Student student) {
		String query = "update students set name=?,age=?,address=? where id=?";
		try {
			connection = JdbcUtil.getConnection();
			if(connection!=null) {
				statement = connection.prepareStatement(query);
				if(statement!=null) {
					statement.setString(1, student.getSname());
					statement.setInt(2, student.getSage());
					statement.setString(3, student.getSaddress());
					statement.setInt(4, student.getSid());
					String status = "failure";
					int rowaffected = statement.executeUpdate();
					if(rowaffected==1)	return "success";
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "failure";
	}

}
