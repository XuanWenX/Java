package hrapp.entity;

import java.util.Date;

public class Employee {

	public Employee() {
		
	}
	
	private Integer eno;
	private String ename;
	private Float salary;
	private String dname;
	private Date hiredate;
	
	public Integer getEno() {
		return eno;
	}
	public void setEno(Integer eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	

	
	
}
 