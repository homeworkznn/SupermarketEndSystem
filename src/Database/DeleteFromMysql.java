package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFromMysql {
	static ConnectToMysql c = new ConnectToMysql();
	Connection con = c.getcon();
	public void deleteGoodsType(long GoodsTypeID) {
		try {
			String strsql = "delete from GoodsType where GoodsTypeID=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, GoodsTypeID);
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
	public void deleteGoodsPicture(int PictureID) {
		try {
			String strsql = "delete from GoodsPicture where PictureID=?)";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, PictureID);
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
	public void deleteGoods(String GoodsName) {
		try {
			String strsql = "delete from Goods where GoodsName=?";
			PreparedStatement psql = con.prepareStatement(strsql);

			psql.setString(1, GoodsName);
			
			ResultSet rs = psql.executeQuery();
			con.commit();
			psql.close();
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
	public int deleteOrders(long OrderID) {
		try {
			String strsql = "delete from Orders where OrderID="+OrderID;
			PreparedStatement psql = con.prepareStatement(strsql);
			//psql.setLong(1, OrderID);
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
	public void deleteGoodsInOneOrder(long OrderID) {
		try {
			String strsql = "delete from GoodsInOneOrder where OrderID=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, OrderID);

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
	public int deletechips(String chipID) {
		try {
			String strsql = "delete from chips where chipID = '"+chipID+"'";
			PreparedStatement psql = con.prepareStatement(strsql);
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
			}
			e.printStackTrace();
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 1;
	}

	public void deleteChipsState(long StateID) {
		try {
			String strsql = "delete from ChipsState where StateID =?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, StateID);
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

	public void deleteRole(int RoleID) {
		try {
			String strsql = "delete from Role where RoleID =?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, RoleID);
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
	public int deleteAdmin(int AdminID) {
		try {
			String strsql = "delete from Administrator where AdminID="+AdminID;
			PreparedStatement psql = con.prepareStatement(strsql);
			//psql.setInt(1, AdminID);
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
	public void deletePermissionToRole(int PermissionID) {
		try {
			String strsql = "delete from PermissionToRole where PermissionID=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, PermissionID);
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
	public void deletePermissionToPage(int PermissionID) {
		try {
			String strsql = "delete from PermissionToPage where PermissionID=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, PermissionID);
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
