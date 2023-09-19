package hrapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import hrapp.command.Command;

public class PstmtQueryCommand implements Command {
	public void execute() {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("输入部门名称");
		try (Scanner in = new Scanner(System.in)) {
			String pdname = in.next();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true","root","xwxx");
				String sql = "select * from employee where dname =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pdname);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Integer eno = rs.getInt(1);
			    	String ename = rs.getString("ename");
			    	String dname = rs.getString("dname");
			    	Float salary = rs.getFloat("salary");
			    	System.out.println("员工信息[Info]："+ dname + "-"
			    	+ eno +"-"+ ename + "-" + salary);
					
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
					try {
						
					
					if(pstmt !=null) {
						pstmt.close();
					}
					if(conn!=null&& conn.isClosed()==false) {
						conn.close();
					}} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}
		}
		
	};


