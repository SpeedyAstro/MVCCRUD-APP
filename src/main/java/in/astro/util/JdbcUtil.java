package in.astro.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



public class JdbcUtil {
	private JdbcUtil() {
		
	}
	static {
		// Step 1: load and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException, IOException {
		// logical connection
		HikariConfig config = new HikariConfig("C:\\\\Users\\\\pande\\\\Documents\\\\eclipse-workspace\\\\servletprgms\\\\JDBCCRUDAPP\\\\src\\\\main\\\\java\\\\in\\\\astro\\\\properties\\\\application.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
	}
	// Physical Connection approach 
	@SuppressWarnings("unused")
	private static Connection physicalConnection() throws FileNotFoundException, IOException, SQLException {
		FileInputStream fis = new FileInputStream("C:\\Users\\pande\\Documents\\eclipse-workspace\\servletprgms\\JDBCCRUDAPP\\src\\main\\java\\in\\astro\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		return DriverManager.getConnection(url,username,password);
	}
	public static void cleanUp(Connection con,ResultSet resultset,Statement statement) throws SQLException {
		if(resultset!=null) resultset.close();
		if(statement!=null) statement.close();
		if(con!=null) con.close();
	}
}