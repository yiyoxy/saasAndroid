package com.herotculb.qunhaichat.dto;

import java.util.Date;

public class MeetingDto {
	private long id;
	private long conpanyId;
	private boolean IPTest;
	private String ipAddress;
	private long managerUserId;
	private String managerUserTrueName;
	private String managerDate;
	private String startDate;//上班时间
	private String endDate;//下班时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getConpanyId() {
		return conpanyId;
	}
	public void setConpanyId(long conpanyId) {
		this.conpanyId = conpanyId;
	}
	public boolean isIPTest() {
		return IPTest;
	}
	public void setIPTest(boolean iPTest) {
		IPTest = iPTest;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public long getManagerUserId() {
		return managerUserId;
	}
	public void setManagerUserId(long managerUserId) {
		this.managerUserId = managerUserId;
	}
	public String getManagerUserTrueName() {
		return managerUserTrueName;
	}
	public void setManagerUserTrueName(String managerUserTrueName) {
		this.managerUserTrueName = managerUserTrueName;
	}
	public String getManagerDate() {
		return managerDate;
	}
	public void setManagerDate(String managerDate) {
		this.managerDate = managerDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
