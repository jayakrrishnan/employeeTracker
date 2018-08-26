/**
 * 
 */
package com.technomak.employeetracker.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author JaY
 *
 */
@Entity
@Table(name = "employee_transfer_record")
public class EmployeeTransferRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_transfer_record_id")
	Integer empTransRecordId;
	@Column(name = "emp_id")
	Integer empId;
	@Column(name = "empName")
	String empName;
	@Column(name = "designation")
	String designation;
	@Column(name = "department")
	String department;
	@Column(name = "transferred_From")
	String transferedFrom;
	@Column(name = "transferred_To")
	String transferedTo;
	@Column(name = "date_of_Transfer")
	Timestamp dateOfTransfer;

	/**
	 * @return the empTransRecordId
	 */
	public Integer getEmpTransRecordId() {
		return empTransRecordId;
	}

	/**
	 * @param empTransRecordId the empTransRecordId to set
	 */
	public void setEmpTransRecordId(Integer empTransRecordId) {
		this.empTransRecordId = empTransRecordId;
	}

	/**
	 * @return the empId
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the transferedFrom
	 */
	public String getTransferedFrom() {
		return transferedFrom;
	}

	/**
	 * @param transferedFrom the transferedFrom to set
	 */
	public void setTransferedFrom(String transferedFrom) {
		this.transferedFrom = transferedFrom;
	}

	/**
	 * @return the transferedTo
	 */
	public String getTransferedTo() {
		return transferedTo;
	}

	/**
	 * @param transferedTo the transferedTo to set
	 */
	public void setTransferedTo(String transferedTo) {
		this.transferedTo = transferedTo;
	}

	/**
	 * @return the dateOfTransfer
	 */
	public Timestamp getDateOfTransfer() {
		return dateOfTransfer;
	}

	/**
	 * @param dateOfTransfer the dateOfTransfer to set
	 */
	public void setDateOfTransfer(Timestamp dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeTransferRecord [empTransRecordId=" + empTransRecordId + ", empId=" + empId + ", empName="
				+ empName + ", designation=" + designation + ", department=" + department + ", transferedFrom="
				+ transferedFrom + ", transferedTo=" + transferedTo + ", dateOfTransfer=" + dateOfTransfer + "]";
	}

}
