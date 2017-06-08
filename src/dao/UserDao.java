package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.util.JdbcUtil;


/**
 * @author Andory
 *
 */
public class UserDao {

	public boolean isExist(String username, String password) {
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		Connection conn = null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
			JdbcUtil.close(rs, pstmt, conn);
		}
		return false;
	}
	
}
