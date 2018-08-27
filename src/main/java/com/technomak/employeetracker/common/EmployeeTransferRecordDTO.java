/**
 * 
 */
package com.technomak.employeetracker.common;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author JaY
 *
 */
public class EmployeeTransferRecordDTO {

	Integer empTransRecordId;
	@NotNull(message = "empId should not be null")
	Integer empId;
	@NotEmpty(message = "empName should not be null")
	String empName;
	@NotEmpty(message = "designation should not be null")
	String designation;
	@NotEmpty(message = "department should not be null")
	String department;
	@NotEmpty(message = "transferedFrom should not be null")
	String transferedFrom;
	@NotEmpty(message = "transferedTo should not be null")
	String transferedTo;
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
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
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
