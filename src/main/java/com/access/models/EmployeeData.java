package com.access.models;

import java.io.Serializable;

public class EmployeeData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String sapID;
	private Long contractID;
	private String enterpriseID;
	private String employeeName;
	private String level;
	private Long assignedCapacity;
	private String rollOnDate;
	private String rollOffDate;
	private String deskAssigned;
	private String laptopID;
	private String laptopSN;
	private String primarySkill;
	private String secondarySkill;
	private String proficiencyLevelPrimary;
	private String proficiencyLevelSecondary;
	
	public String getSapID() {
		return sapID;
	}
	public void setSapID(String sapID) {
		this.sapID = sapID;
	}
	public Long getContractID() {
		return contractID;
	}
	public void setContractID(Long contractID) {
		this.contractID = contractID;
	}
	public String getEnterpriseID() {
		return enterpriseID;
	}
	public void setEnterpriseID(String enterpriseID) {
		this.enterpriseID = enterpriseID;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Long getAssignedCapacity() {
		return assignedCapacity;
	}
	public void setAssignedCapacity(Long assignedCapacity) {
		this.assignedCapacity = assignedCapacity;
	}
	public String getRollOnDate() {
		return rollOnDate;
	}
	public void setRollOnDate(String rollOnDate) {
		this.rollOnDate = rollOnDate;
	}
	public String getRollOffDate() {
		return rollOffDate;
	}
	public void setRollOffDate(String rollOffDate) {
		this.rollOffDate = rollOffDate;
	}
	public String getDeskAssigned() {
		return deskAssigned;
	}
	public void setDeskAssigned(String deskAssigned) {
		this.deskAssigned = deskAssigned;
	}
	public String getLaptopID() {
		return laptopID;
	}
	public void setLaptopID(String laptopID) {
		this.laptopID = laptopID;
	}
	public String getLaptopSN() {
		return laptopSN;
	}
	public void setLaptopSN(String laptopSN) {
		this.laptopSN = laptopSN;
	}
	public String getPrimarySkill() {
		return primarySkill;
	}
	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}
	public String getSecondarySkill() {
		return secondarySkill;
	}
	public void setSecondarySkill(String secondarySkill) {
		this.secondarySkill = secondarySkill;
	}
	public String getProficiencyLevelPrimary() {
		return proficiencyLevelPrimary;
	}
	public void setProficiencyLevelPrimary(String proficiencyLevelPrimary) {
		this.proficiencyLevelPrimary = proficiencyLevelPrimary;
	}
	public String getProficiencyLevelSecondary() {
		return proficiencyLevelSecondary;
	}
	public void setProficiencyLevelSecondary(String proficiencyLevelSecondary) {
		this.proficiencyLevelSecondary = proficiencyLevelSecondary;
	}
	
	@Override
	public String toString() {
		return "EmployeeData [sapID=" + sapID + ", contractID=" + contractID + ", enterpriseID=" + enterpriseID
				+ ", employeeName=" + employeeName + ", level=" + level + ", assignedCapacity=" + assignedCapacity
				+ ", rollOnDate=" + rollOnDate + ", rollOffDate=" + rollOffDate + ", deskAssigned=" + deskAssigned
				+ ", laptopID=" + laptopID + ", laptopSN=" + laptopSN + ", primarySkill=" + primarySkill
				+ ", secondarySkill=" + secondarySkill + ", proficiencyLevelPrimary=" + proficiencyLevelPrimary
				+ ", proficiencyLevelSecondary=" + proficiencyLevelSecondary + "]";
	}	
}
