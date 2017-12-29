package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddToMysql {
	static ConnectToMysql c = new ConnectToMysql();
	Connection con = c.getcon();
	public void addGoodsType(long GoodsTypeID, String TypeName) {
		try {
			String strsql = "insert into GoodsType (GoodsTypeID,TypeName) values(?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, GoodsTypeID);
			psql.setString(2, TypeName);
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
			rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void addGoodsPicture(int PictureID, long GoodsTypeID) {
		try {
			String strsql = "insert into GoodsPicture (PictureID,GoodsTypeID) values(?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, PictureID);
			psql.setLong(2, GoodsTypeID);
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
			rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int addGoods(long GoodsID, int TypeID, String GoodsName, float Price, String ProductAddress, int Number,
			String DateOfManufacture, String DateOfStock) {
		try {
			String strsql = "insert into Goods (GoodsID,TypeID,GoodsName,Price,ProductAddress,Number,DateOfManufacture,DateOfStock,ifMatch) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, GoodsID);
			psql.setInt(2, TypeID);
			psql.setString(3, GoodsName);
			psql.setFloat(4, Price);
			psql.setString(5, ProductAddress);
			psql.setInt(6, Number);
			psql.setString(7, DateOfManufacture);
			psql.setString(8, DateOfStock);
			psql.setInt(9, 0);
			//ResultSet rs = psql.executeQuery();
			psql.executeUpdate();
			con.commit();
			psql.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}
	public int addOrders(long OrderID, String CreateTime,int MachineNum,float TotalPrice,int TotalNum) {
		try {
			String strsql = "insert into Orders (OrderID,CreateTime,MachineNum,TotalPrice,TotalNum) values(?,?,?,?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, OrderID);
			psql.setString(2, CreateTime);
			psql.setInt(3, MachineNum);
			psql.setFloat(4,TotalPrice);
			psql.setInt(5,TotalNum);
			psql.executeUpdate();
			
			psql.close();

			con.commit();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}
	public int addGoodsInOneOrder(long OrderID, long GoodsID,int num) {
		try {
			String strsql = "insert into GoodsInOneOrder (OrderID,GoodsID,Num) values(?,?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, OrderID);
			psql.setLong(2, GoodsID);
			psql.setInt(3, num);
			//ResultSet rs = psql.executeQuery();
			psql.executeUpdate();
			con.commit();
			psql.close();
			//rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}
	public int addchips(String chipID, long GoodsID,int State) {
		try {
			String strsql = "insert into chips (chipID,GoodsID,State) values(?,?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setString(1, chipID);
			psql.setLong(2, GoodsID);
			psql.setInt(3, State);
			//ResultSet rs = psql.execute();
			psql.executeUpdate();
			con.commit();
			psql.close();
			//rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}

	public void addChipsState(long StateID, String StateName) {
		try {
			String strsql = "insert into ChipsState (StateID,StateName) values(?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, StateID);
			psql.setString(2, StateName);
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
			rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void addRole(int RoleID, String RoleName) {
		try {
			String strsql = "insert into Role (RoleID,RolreName) values(?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, RoleID);
			psql.setString(2, RoleName);
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
			rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int addAdmin(int AdminID, String Name,int RoleID,long TelNum ,String Password) {
		try {
			String strsql = "insert into Administrator (AdminID,NickName,RoleID,TelNum,Password) values(?,?,?,?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, AdminID);
			psql.setString(2, Name);
			psql.setInt(3, RoleID);
			psql.setLong(4, TelNum);
			psql.setString(5, Password);
			//ResultSet rs = psql.executeQuery();
			psql.executeUpdate();
			con.commit();
			psql.close();
			//rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return 0;
			}
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}
	public void addPermissionToRole(int PermissionID, int RoleID) {
		try {
			String strsql = "insert into PermissionToRole (PermissionID,RolreID) values(?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, PermissionID);
			psql.setInt(2, RoleID);
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
			rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void addPermissionToPage(int PermissionID, int PageID) {
		try {
			String strsql = "insert into PermissionToPage (PermissionID,PageID) values(?,?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, PermissionID);
			psql.setInt(2, PageID);
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
			rs.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
