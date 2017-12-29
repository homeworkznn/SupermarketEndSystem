package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayFromMysql {
	static ConnectToMysql c = new ConnectToMysql();
	Connection con = c.getcon();

	public void displayGoods() {
		try {
			String strsql = "select * from goods";
			PreparedStatement psql = con.prepareStatement(strsql);
			ResultSet rs = psql.executeQuery();
			long id = 0, typeid = 0, num = 0;
			String goodsname, address, date1, date2;
			float price;
			while (rs.next()) {
				// 获取这列数据
				id = rs.getInt("GoodsID");
				typeid = rs.getInt("TypeID");
				goodsname = rs.getString("GoodsName");
				price = rs.getFloat("Price");
				address = rs.getString("ProductAddress");
				num = rs.getInt("Number");
				date1 = rs.getString("DateOfManufacture");
				date2 = rs.getString("DateOfStock");
				System.out.println(id + "\t" + typeid + "\t" + goodsname + "\t" + price + "\t" + address + "\t"
						+ address + "\t\t" + num + "\t\t" + date1 + "\t" + date2);
			}
			rs.close();
			psql.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void displayOrders() {
		try {
			String strsql = "select * from orders";
			PreparedStatement psql = con.prepareStatement(strsql);
			ResultSet rs = psql.executeQuery();
			long OrderID = 0;
			String CreateTime=null;
			int MachineNum=0;
			float TotalPrice=0;
			String Content =null;
			while (rs.next()) {
				// 获取这列数据
				OrderID = rs.getLong("OrderID");
				CreateTime = rs.getString("CreateTime");
				MachineNum = rs.getInt("MachineNum");
				Content= rs.getString("Content");
				TotalPrice = rs.getFloat("TotalPrice");
				
			}
			rs.close();
			psql.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	


}
