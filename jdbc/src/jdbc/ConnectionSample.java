package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionSample {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    //connection with database account and its passwords
	    String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "xwxx";
	    conn = DriverManager.getConnection(url,username,password);
	    System.out.println(conn);
	    
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery("Select * FROM employee");
	    
	    while(rs.next()) {
	    	Integer eno = rs.getInt(1);
	    	String ename = rs.getString("ename");
	    	String dname = rs.getString("dname");
	    	Float salary = rs.getFloat("salary");
	    	System.out.println("员工信息[Info]："+ dname + "-"
	    	+ eno +"-"+ ename + "-" + salary);
	    }
	    
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null && conn.isClosed() == false) {
					conn.close();
				}
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}

}
