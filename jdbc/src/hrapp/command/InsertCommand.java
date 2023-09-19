package hrapp.command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import jdbc.common.DbUtils;

public class InsertCommand implements Command{
	public void execute() {
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("������Ա�����");
			int eno = in.nextInt();
			System.out.println("������Ա������");
			String ename = in.next();
			System.out.println("������Ա��н��");
			float salary = in.nextFloat();
			System.out.println("��������������");
			String dname = in.next();
			System.out.println("��������ְ����");
			String strHiredate = in.next();
			java.util.Date udHiredate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				udHiredate = sdf.parse(strHiredate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long time = udHiredate.getTime();
			java.sql.Date sdHiredate = new java.sql.Date(time);
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DbUtils.getConnection();
				String sql = "insert into employee(eno,ename,salary,dname,hiredate) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eno);
				pstmt.setString(2, ename);
				pstmt.setFloat(3, salary);
				pstmt.setString(4, dname);
				pstmt.setDate(5, sdHiredate);
				int cnt = pstmt.executeUpdate();
				System.out.println(cnt);
				System.out.println(ename + "Ա����ְ�����Ѱ���");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DbUtils.closeConnection(null, pstmt, conn);
			}
		}
	}

}
