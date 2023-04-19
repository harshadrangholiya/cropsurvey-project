package com.cropsurvey.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "survey")
public class Survey {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Boolean Status;
	
	private String remarks;
	
	private String state;
	
	private String district;
	
	private String taluka;
	private String village;
	private Double cu_latitude;
	
	private Double cu_longitude;
	private Double su_latitude;
	private Double su_longitude;
	private String date;
	
	public Survey() {
		
	}
	
	public Survey(Long id, String name, Boolean status, String remarks, String state, String district, String taluka,
			String village, Double cu_latitude, Double cu_longitude, Double su_latitude, Double su_longitude, String date
			) {
		super();
		this.id = id;
		this.name = name;
		Status = status;
		this.remarks = remarks;
		this.state = state;
		this.district = district;
		this.taluka = taluka;
		this.village = village;
		this.cu_latitude = cu_latitude;
		this.cu_longitude = cu_longitude;
		this.su_latitude = su_latitude;
		this.su_longitude = su_longitude;
		this.date=date;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return Status;
	}
	public void setStatus(Boolean status) {
		Status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTaluka() {
		return taluka;
	}
	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public Double getCu_latitude() {
		return cu_latitude;
	}
	public void setCu_latitude(Double cu_latitude) {
		this.cu_latitude = cu_latitude;
	}
	public Double getCu_longitude() {
		return cu_longitude;
	}
	public void setCu_longitude(Double cu_longitude) {
		this.cu_longitude = cu_longitude;
	}
	public Double getSu_latitude() {
		return su_latitude;
	}
	public void setSu_latitude(Double su_latitude) {
		this.su_latitude = su_latitude;
	}
	public Double getSu_longitude() {
		return su_longitude;
	}
	public void setSu_longitude(Double su_longitude) {
		this.su_longitude = su_longitude;
	}
	

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", name=" + name + ", Status=" + Status + ", remarks=" + remarks + ", state="
				+ state + ", district=" + district + ", taluka=" + taluka + ", village=" + village + ", cu_latitude="
				+ cu_latitude + ", cu_longitude=" + cu_longitude + ", su_latitude=" + su_latitude + ", su_longitude="
				+ su_longitude + ", date=" + date + "]";
	}
	
}

