package com.ifi.profile.model;

public class Task {
	private String project;
	private String chargeid;
	private String proStatus;
	private String proDescription;
	private String proDomain;
	private String customer;
	private String startdate;
	private String finishdate;
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
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
	public String getChargeid() {
		return chargeid;
	}
	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getFinishdate() {
		return finishdate;
	}
	public void setFinishdate(String finishdate) {
		this.finishdate = finishdate;
	}
	
	
	
}
