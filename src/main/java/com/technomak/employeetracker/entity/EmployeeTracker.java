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
@Table(name = "master_list")
public class EmployeeTracker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	Integer empId;
	@Column(name = "emp_name")
	String empName;
	@Column(name = "designation")
	String designation;
	@Column(name = "department")
	String department;
	@Column(name = "location")
	String location;
	@Column(name = "current_prj")
	String currentProject;
	@Column(name = "next_prj")
	String nextProject;
	@Column(name = "loaded_upto")
	Timestamp loadedUpto;
	@Column(name = "skill")
	String skill;
	@Column(name = "week_no")
	Integer weekNo;
	@Column(name = "leave_status")
	Integer leaveStatus;
	@Column(name = "visa_expiry")
	Timestamp visaExpiry;
	@Column(name = "rating")
	String rating;
	@Column(name = "trade")
	String trade;
	@Column(name = "remarks")
	String remark;

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
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the currentProject
	 */
	public String getCurrentProject() {
		return currentProject;
	}

	/**
	 * @param currentProject the currentProject to set
	 */
	public void setCurrentProject(String currentProject) {
		this.currentProject = currentProject;
	}

	/**
	 * @return the nextProject
	 */
	public String getNextProject() {
		return nextProject;
	}

	/**
	 * @param nextProject the nextProject to set
	 */
	public void setNextProject(String nextProject) {
		this.nextProject = nextProject;
	}

	/**
	 * @return the loadedUpto
	 */
	public Timestamp getLoadedUpto() {
		return loadedUpto;
	}

	/**
	 * @param loadedUpto the loadedUpto to set
	 */
	public void setLoadedUpto(Timestamp loadedUpto) {
		this.loadedUpto = loadedUpto;
	}

	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}

	/**
	 * @param skill the skill to set
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}

	/**
	 * @return the weekNo
	 */
	public Integer getWeekNo() {
		return weekNo;
	}

	/**
	 * @param weekNo the weekNo to set
	 */
	public void setWeekNo(Integer weekNo) {
		this.weekNo = weekNo;
	}

	/**
	 * @return the leaveStatus
	 */
	public Integer getLeaveStatus() {
		return leaveStatus;
	}

	/**
	 * @param leaveStatus the leaveStatus to set
	 */
	public void setLeaveStatus(Integer leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	/**
	 * @return the visaExpiry
	 */
	public Timestamp getVisaExpiry() {
		return visaExpiry;
	}

	/**
	 * @param visaExpiry the visaExpiry to set
	 */
	public void setVisaExpiry(Timestamp visaExpiry) {
		this.visaExpiry = visaExpiry;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the trade
	 */
	public String getTrade() {
		return trade;
	}

	/**
	 * @param trade the trade to set
	 */
	public void setTrade(String trade) {
		this.trade = trade;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmployeeTracker [empId=" + empId + ", empName=" + empName + ", designation=" + designation
				+ ", department=" + department + ", location=" + location + ", currentProject=" + currentProject
				+ ", nextProject=" + nextProject + ", loadedUpto=" + loadedUpto + ", skill=" + skill + ", weekNo="
				+ weekNo + ", leaveStatus=" + leaveStatus + ", visaExpiry=" + visaExpiry + ", rating=" + rating
				+ ", trade=" + trade + ", remark=" + remark + "]";
	}
}
