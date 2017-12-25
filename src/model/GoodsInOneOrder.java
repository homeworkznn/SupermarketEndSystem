package model;

import java.util.Date;

public class GoodsInOneOrder{
	public long id;
	public int type;
	public String goodsName;
	public float singlePrice;
	public float totalPrice;
	public int totalNum;
	public String producerAddress;
	public Date dateOfManufacture;
	public Date dateOfStock;
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
	public float getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(float singlePrice) {
		this.singlePrice = singlePrice;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
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
