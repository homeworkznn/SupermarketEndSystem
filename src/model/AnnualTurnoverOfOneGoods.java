package model;

public class AnnualTurnoverOfOneGoods {
	public int     goodsType;  //商品类型
	public String  goodsTypeName;  //商品类型名称
	public int     goodsId;     //商品id
	public String  goodsName;   //商品名称
	public float   annualAverageUnitPrice;   //年平均单价（元）
	public int     annualTurnover;   //年销售量
	public float   annualIncome;  //年总销售额
	public String     year;  //所属年份
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public float getAnnualAverageUnitPrice() {
		return annualAverageUnitPrice;
	}
	public void setAnnualAverageUnitPrice(float annualAverageUnitPrice) {
		this.annualAverageUnitPrice = annualAverageUnitPrice;
	}
	public int getAnnualTurnover() {
		return annualTurnover;
	}
	public void setAnnualTurnover(int annualTurnover) {
		this.annualTurnover = annualTurnover;
	}
	public float getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(float annualIncome) {
		this.annualIncome = annualIncome;
	}
}
