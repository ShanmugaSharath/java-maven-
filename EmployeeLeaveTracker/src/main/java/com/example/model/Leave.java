package com.example.model;

public class Leave {
    public int empId;
    public String fromDate;
    public String toDate;
    public String leaveType;
    
    public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

    
    public Leave(int empId, String fromDate, String toDate,String leaveType) {
        this.empId = empId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.leaveType=leaveType;
    }
}