package model;

public class AnnualTurnoverOfOneGoods {
	public int     goodsType;  //��Ʒ����
	public String  goodsTypeName;  //��Ʒ��������
	public int     goodsId;     //��Ʒid
	public String  goodsName;   //��Ʒ����
	public float   annualAverageUnitPrice;   //��ƽ�����ۣ�Ԫ��
	public int     annualTurnover;   //��������
	public float   annualIncome;  //�������۶�
	public String     year;  //�������
	
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
