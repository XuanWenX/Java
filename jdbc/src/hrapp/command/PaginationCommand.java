package hrapp.command;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hrapp.entity.Employee;
import jdbc.common.DbUtils;

public class PaginationCommand implements Command{

	public void execute() {
		Scanner in=null;
		try {
			in = new Scanner(System.in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("请输入员工页数：");
		int page = in.nextInt();
		Connection conn = null;
		PreparedStatement pstmt = null;
		@SuppressWarnings("unused")
		ResultSet rs = null;
		List<Employee>list = new ArrayList<>();
		try {
			conn = DbUtils.getConnection();
			String sql = "select * from employee limit ?,10";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (page-1)*10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer eno = rs.getInt("eno");
				String ename = rs.getString("ename");
				Float salary = rs.getFloat("salary");
				String dname = rs.getString("dname");
				Employee emp = new Employee();
				Date hiredate = rs.getDate("hiredate");
				emp.setEno(eno);
				emp.setEname(ename);
				emp.setSalary(salary);
				emp.setDname(dname);
				emp.setHiredate(hiredate);
				list.add(emp);
			}
			System.out.println(list.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(rs, pstmt, conn); 
		}
	}
}
