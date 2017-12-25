package model;

import java.util.Date;
import java.util.List;

public class Order{
	public long id;
	public Date createTime;
	public int machineNum;
	public float price;
	public int totalNum;
	public List<GoodsInOneOrder> goodsInOneOrder;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getMachineNum() {
		return machineNum;
	}
	public void setMachineNum(int machineNum) {
		this.machineNum = machineNum;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public List<GoodsInOneOrder> getGoodsInOneOrder(){
		return goodsInOneOrder;
	}
	public void setGoodsInOneOrder(List<GoodsInOneOrder> goodsInOneOrder) {
		this.goodsInOneOrder = goodsInOneOrder;
	}
	
}
