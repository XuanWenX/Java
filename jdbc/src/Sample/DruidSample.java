package Sample;

import java.io.FileInputStream;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import jdbc.common.DbUtils;

public class DruidSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = new Properties();
		String propertyFile = DruidSample.class.getResource("/druid_config.properties").getPath();
        try {
        	
			propertyFile =  new URLDecoder().decode(propertyFile,"UTF-8");
		    properties.load(new FileInputStream(propertyFile));
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Connection conn =null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
			DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
		    conn = dataSource.getConnection();
		    pstmt = conn.prepareStatement("select*from employee limit 0,10");
            rs = pstmt.executeQuery();
            while(rs.next()){
				Integer eno = rs.getInt(1);
		    	String ename = rs.getString("ename");
		    	String dname = rs.getString("dname");
		    	Float salary = rs.getFloat("salary");
		    	System.out.println("员工信息[Info]："+ dname + "-"
		    	+ eno +"-"+ ename + "-" + salary);
				
			}
            
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeConnection(rs, pstmt, conn);
		}
        
	}

}
