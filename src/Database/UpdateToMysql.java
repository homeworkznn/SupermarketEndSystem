package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UpdateToMysql {
	static ConnectToMysql c = new ConnectToMysql();
	Connection con = c.getcon();
	
	public void UpdateAdmin(int AdminID,String Str,long num, int n) {//修改账户信息
		String strsql=null;
		PreparedStatement psql=null;
		try {
			if(n==0) {
				 strsql = "update Administrator set NickName=? where AdminID=?";
				 psql = con.prepareStatement(strsql);
				 psql.setString(1, Str);
				 psql.setInt(2, AdminID);
			}
			if(n==1) {
				 strsql = "update Administrator set RoleID=? where AdminID=?";
				 psql = con.prepareStatement(strsql);
				 psql.setLong(1, num);
				 psql.setInt(2, AdminID);
			}
			if(n==2) {
				 strsql = "update Administrator set TelNum=? where AdminID=?";
				 psql = con.prepareStatement(strsql);
				 psql.setLong(1,num);
				 psql.setInt(2, AdminID);
			}
			if(n==3) {
				 strsql = "update Administrator set TPassword=? where AdminID=?";
				 psql = con.prepareStatement(strsql);
				 psql.setString(1, Str);
				 psql.setInt(2, AdminID);
			}
			
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
	public int UpdateChipsState(String ChipID,int State) {//修改芯片信息
		String strsql=null;
		PreparedStatement psql=null;
		try {
			strsql = "update Chips set State="+State+" where chipID='"+ChipID+"'";
			psql = con.prepareStatement(strsql);		
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
	
	public int UpdateGoodsIdOfOneChipByChipId(String ChipID,int goodsId) {//修改芯片信息
		String strsql=null;
		PreparedStatement psql=null;
		try {
			strsql = "update Chips set GoodsID="+goodsId+" where chipID='"+ChipID+"'";
			psql = con.prepareStatement(strsql);		
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
	
	public int UpdateUserInfoById(User user){
		String strsql=null;
		PreparedStatement psql=null;
		try {
			strsql = "update Administrator set NickName='"+user.getName()+"',"+"RoleID="+user.role+",TelNum="+user.tel+",Password='"+user.password+"'  where AdminID="+user.id+"";
			psql = con.prepareStatement(strsql);		
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
	
	public int  UpdateGoodsPicture(long GoodsID,String picurl) {//修改商品图片
		String strsql=null;
		PreparedStatement psql=null;
		try {
			
		    strsql = "update Goods set PicUrl=? where GoodsID=?";
		 	psql = con.prepareStatement(strsql);
		    psql.setString(1, picurl);
		    psql.setLong(2, GoodsID);
					
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
}
