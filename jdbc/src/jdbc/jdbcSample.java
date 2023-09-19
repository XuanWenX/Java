package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; 

public class jdbcSample {
    public static void main(String[] args) {
    	 Connection conn = null;
    	 try {
    		 //ע������
		 Class.forName("com.mysql.cj.jdbc.Driver");
		     //�����������ݿ�
		 conn = DriverManager.getConnection(
				 "jdbc:mysql:/localhost:3306/",
				 "root","xwxx");
		     //ע��statement
		 Statement stmt = conn.createStatement();
		 ResultSet rs = stmt.executeQuery("select * from employee");
		    //�������
		 while(rs.next()) {
			 Integer eno = rs.getInt(1);
			 String ename = rs.getString("ename");
			 Float salary = rs.getFloat("salary");
			 String dname = rs.getString("dname");
			 System.out.println(dname + "-" + eno +"-"+ ename +"-"+salary);
		 }
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }finally {
    		 try {
    		 if(conn != null && conn.isClosed()== false) {
    			 conn.close();
    		 }}catch(Exception ex){
    			 ex.printStackTrace();
    		 }
    		 
		}
		 
		 
		 
	}

}
