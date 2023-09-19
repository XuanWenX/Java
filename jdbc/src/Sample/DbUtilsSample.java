package Sample;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import hrapp.entity.Employee;

public class DbUtilsSample{
    
	private static void query() {
		Properties properties = new Properties();
		String propertyFile = DbUtilsSample.class.getResource("druid_config.properties").getPath();
		try {
			
			propertyFile =  new URLDecoder.decode(propertyFile,"UTF-8");
			
		    properties.load(new FileInputStream(propertyFile));
		    
		    DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
			QueryRunner qr = new QueryRunner(dataSource);
			List<Employee>list = qr.query("select * from employee limit ?,10", 
			    		new BeanListHandler<>(Employee.class),
			    		new Object[] {10});
		    for(Employee emp:list) {
		        	System.out.println(emp.getEname());
		    }
		    
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		
		
	}
	
	public static void update() {
		Properties properties = new Properties();
		String propertyFile = DbUtilsSample.class.getResource("druid_config.properties").getPath();
		Connection conn = null;
		try {
			
			propertyFile =  new URLDecoder.decode(propertyFile,"UTF-8");
			
		    properties.load(new FileInputStream(propertyFile));
		    
		    DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
			conn = dataSource.getConnection();
		    conn.setAutoCommit(false);
		    String sql1="update employee set salary=salary+1000 where eno = ?";
		    String sql2="update employee set salary=salary-1000 where eno = ?";
		    QueryRunner qr = new QueryRunner();
		    qr.update(conn,sql1,new Object[]{2});
		    qr.update(conn,sql1,new Object[]{3});
		    conn.commit();
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
				if(conn!=null && !conn.isClosed()) {
					conn.rollback();
				}}catch(Exception e) {
					e.printStackTrace();
				}
			} finally {
				if(conn!=null && !conn.isClosed()) {
					conn.close();
			}}
		
	}	
	
	public static void main(String[] args) {
		query();
		update();
	}

}
