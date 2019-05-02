package com.ifi.profile.model;

public class Task {
	private String project;
	private String chargeId;
	private String proStatus;
	private String proDescription;
	private String proDomain;
	private String customer;
	private String startDate;
	private String finishDate;
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
	public String getChargeId() {
		return chargeId;
	}
	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}
	
	public String getProStatus() {
		return proStatus;
	}
	
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	
	public String getProDescription() {
		return proDescription;
	}
	
	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}
	
	public String getProDomain() {
		return proDomain;
	}
	
	public void setProDomain(String proDomain) {
		this.proDomain = proDomain;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
}
