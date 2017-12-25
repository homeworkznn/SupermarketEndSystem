package model;

import java.util.Date;

public class Goods{
	public long id;
	public int type;
	public String goodsName;
	public float price;
	public String producerAddress;
	public Date dateOfManufacture;
	public Date dateOfStock;
	public String picUrl;
	public String chipId;
	
	
	public String getChipId() {
		return chipId;
	}
	public void setChipId(String chipId) {
		this.chipId = chipId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getProducerAddress() {
		return producerAddress;
	}
	public void setProducerAddress(String producerAddress) {
		this.producerAddress = producerAddress;
	}
	public Date getDateOfManufacture() {
		return dateOfManufacture;
	}
	public void setDateOfManufacture(Date dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}
	public Date getDateOfStock() {
		return dateOfStock;
	}
	public void setDateOfStock(Date dateOfStock) {
		this.dateOfStock = dateOfStock;
	}
	
}
