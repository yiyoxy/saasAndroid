package com.herotculb.qunhaichat.dto;

import java.util.Date;

public class InOrderDto {
	private long id;
	private String name;
	private String createDate;
	private String orderNum;
	private int state;
	private long conpanyId;
	private long createUser;
	private String createUserName;
	private long inStoreUser;
	private String inStoreUserName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getConpanyId() {
		return conpanyId;
	}
	public void setConpanyId(long conpanyId) {
		this.conpanyId = conpanyId;
	}
	public long getCreateUser() {
		return createUser;
	}
	public void setCreateUser(long createUser) {
		this.createUser = createUser;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public long getInStoreUser() {
		return inStoreUser;
	}
	public void setInStoreUser(long inStoreUser) {
		this.inStoreUser = inStoreUser;
	}
	public String getInStoreUserName() {
		return inStoreUserName;
	}
	public void setInStoreUserName(String inStoreUserName) {
		this.inStoreUserName = inStoreUserName;
	}
}
