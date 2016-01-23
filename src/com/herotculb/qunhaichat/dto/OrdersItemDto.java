package com.herotculb.qunhaichat.dto;

import java.util.Date;

public class OrdersItemDto {
	private long id;
	private String goodsName;
	private String goodsType;
	private double goodsinPrice;
	private long goodsSourceId;
	private String goodsSourceName;
	private double goodsNum;
	private float price;
	private double score;
	private long goodsToStorehouseId;
	private String goodsToStorehouseName;
	private String createDate;
	private long inOrderId;
	private long goodsId;
	private String codeid;//货物的扫码
	private String spell;//拼音简写
	private String goodsModel;//货物型号
	private long conpanyId;
	private String marks;
	private double countPrice;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public double getGoodsinPrice() {
		return goodsinPrice;
	}
	public void setGoodsinPrice(double goodsinPrice) {
		this.goodsinPrice = goodsinPrice;
	}
	public long getGoodsSourceId() {
		return goodsSourceId;
	}
	public void setGoodsSourceId(long goodsSourceId) {
		this.goodsSourceId = goodsSourceId;
	}
	public String getGoodsSourceName() {
		return goodsSourceName;
	}
	public void setGoodsSourceName(String goodsSourceName) {
		this.goodsSourceName = goodsSourceName;
	}
	public double getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(double goodsNum) {
		this.goodsNum = goodsNum;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public long getGoodsToStorehouseId() {
		return goodsToStorehouseId;
	}
	public void setGoodsToStorehouseId(long goodsToStorehouseId) {
		this.goodsToStorehouseId = goodsToStorehouseId;
	}
	public String getGoodsToStorehouseName() {
		return goodsToStorehouseName;
	}
	public void setGoodsToStorehouseName(String goodsToStorehouseName) {
		this.goodsToStorehouseName = goodsToStorehouseName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public long getInOrderId() {
		return inOrderId;
	}
	public void setInOrderId(long inOrderId) {
		this.inOrderId = inOrderId;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	public String getSpell() {
		return spell;
	}
	public void setSpell(String spell) {
		this.spell = spell;
	}
	public String getGoodsModel() {
		return goodsModel;
	}
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}
	public long getConpanyId() {
		return conpanyId;
	}
	public void setConpanyId(long conpanyId) {
		this.conpanyId = conpanyId;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public double getCountPrice() {
		return countPrice;
	}
	public void setCountPrice(double countPrice) {
		this.countPrice = countPrice;
	}
	
}
