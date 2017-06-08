package dao.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Andory
 *
 */
public class JdbcUtil {
	
	private JdbcUtil(){}
	
	private static String driverName;
	private static String url;
	private static String userName;
	private static String password;
	
	static{
		try{
			Properties prop = new Properties();
			InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			prop.load(is);
			
			driverName = prop.getProperty("driverName");
			url = prop.getProperty("url");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");
			
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
		
	public static Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(url,userName,password);
		return conn;
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}finally{
				try {
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}
}
