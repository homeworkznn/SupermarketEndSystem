package Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Authority;
import model.Chip;
import model.Goods;
import model.GoodsInOneOrder;
import model.GoodsType;
import model.Order;
import model.User;

import java.sql.PreparedStatement;

public class GetDataFromMySql {
	static ConnectToMysql c = new ConnectToMysql();
	Connection con = c.getcon();
	
	public  long AutoGetordernumber() {//生成随机单号
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String paymentID =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 订单ID
	    long ordernumber= Long.parseLong(paymentID);
	    return ordernumber;
	}
	public String selectAdmin_ID_Password(long adminid) {// 查找账户是否存在和并取密码
		String password = null;
		try {
			String strsql = "select password from Administrator where adminid=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setLong(1, adminid);
			ResultSet rs = psql.executeQuery();
			while (rs.next()) {
				// 获取这列数据
				password = rs.getString("password");
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
		return password;
	}
	
	public List<Goods> getAllGoodsInfo(){
		List<Goods> goodsList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		//java.sql.Date date1 = new java.sql.Date(date_of_admission.getTime());
		//String date3=formatter.format(date1);
		String sql = "select * from Goods";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    int columnCount = md.getColumnCount();
		    while (rs.next()) {
		    	Goods goods = new Goods();
		    	goods.setId(rs.getLong("GoodsID"));
		    	goods.setGoodsName(rs.getString("GoodsName"));
		    	goods.setPicUrl(rs.getString("Url"));
		    	goods.setDateOfManufacture(rs.getTimestamp("DateOfManufacture"));
		    	goods.setDateOfStock(rs.getTimestamp("DateOfStock"));
		    	goods.setProducerAddress(rs.getString("ProductAddress"));
		    	goods.setPrice(rs.getFloat("Price"));
		    	goods.setNum(rs.getInt("Num"));
		    	goodsList.add(goods);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return goodsList;
	}
	
	
	public User getUserInfoById(int userId){
		User user = new User();
		
		ResultSet rs;
		String sql = "select * from Administrator where AdminID="+userId;
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    while (rs.next()) {
		    	user.setId(rs.getInt("AdminID"));
		    	user.setName(rs.getString("NickName"));
		    	user.setPassword(rs.getString("Password"));
		    	user.setRole(rs.getInt("RoleID"));
		    	user.setTel(rs.getLong("TelNum"));
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public List<User> getUserInfoByTelOrName(long tel,String name){
		List<User> list = new ArrayList<>();
		
		ResultSet rs;
		String sql = "select * from Administrator where NickName='"+name+"'"+"  or TelNum="+tel;
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    while (rs.next()) {
		    	User user = new User();
		    	user.setId(rs.getInt("AdminID"));
		    	user.setName(rs.getString("NickName"));
		    	user.setPassword(rs.getString("Password"));
		    	user.setRole(rs.getInt("RoleID"));
		    	user.setTel(rs.getLong("TelNum"));
		    	list.add(user);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<Authority> getAllAuthority(){
		List<Authority> authorityList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from UserPermission";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	Authority authority = new Authority();
		    	authority.setId(rs.getInt("ID"));
		    	authority.setLayoutName(rs.getString("PermissionName"));	    	
		    	authorityList.add(authority);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return authorityList;
	}
	
	public List<Chip> getAllChips(){
		List<Chip> chipsList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from Chips";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	Chip chips = new Chip();
		    	chips.setId(rs.getString("chipID"));
		    	chips.setState(rs.getInt("State"));
		    	chips.setGoodsId(rs.getInt("GoodsID"));		    	    	
		    	chipsList.add(chips);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return chipsList;
	}
	
	
	public List<Chip> getChipsGoodsMatchSituationByGoodsId(long GoodsId){
		List<Chip> chipsList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from Chips where GoodsID = "+GoodsId;
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	Chip chips = new Chip();
		    	chips.setId(rs.getString("chipID"));
		    	chips.setState(rs.getInt("State"));
		    	chips.setGoodsId(rs.getInt("GoodsID"));		    	    	
		    	chipsList.add(chips);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return chipsList;
	}
	
	public User getUserByNameOrTel(long tel,String name){
		User user = new User();
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from Administrator where TelNum="+tel+" or NickName='"+name+"'";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	user.setId(rs.getLong("AdminID"));
		    	user.setName(rs.getString("NickName"));
		    	user.setPassword(rs.getString("TPassword"));
		    	user.setRole(rs.getInt("RoleID"));
		    	user.setTel(rs.getLong("TelNum"));
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public List<Goods> getAllGoods(){
		List<Goods> goodsList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from Goods";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	Goods goods = new Goods();
		    	goods.setId(rs.getInt("GoodsID"));
		    	goods.setType(rs.getInt("TypeID"));
		    	goods.setGoodsName(rs.getString("GoodsName"));
		    	goods.setPrice(rs.getFloat("Price"));
		    	goods.setNum(rs.getInt("Num"));
		    	goods.setPicUrl(rs.getString("PicUrl"));
		    	goods.setIfMatchChip(rs.getInt("ifMatch"));
		    	goods.setProducerAddress(rs.getString("ProductAddress"));
		    	goods.setDateOfManufacture(rs.getTimestamp("DateOfManufacture"));
		    	goods.setDateOfStock(rs.getTimestamp("DateOfStock"));
		    	goodsList.add(goods);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return goodsList;
	}
	
	public List<Goods> getAllNotMatchGoods(){
		List<Goods> goodsList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from Goods where ifMatch=0";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	Goods goods = new Goods();
		    	goods.setId(rs.getInt("GoodsID"));
		    	goods.setType(rs.getInt("TypeID"));
		    	goods.setGoodsName(rs.getString("GoodsName"));
		    	goods.setPrice(rs.getFloat("Price"));
		    	goods.setNum(rs.getInt("Num"));
		    	goods.setPicUrl(rs.getString("PicUrl"));
		    	goods.setIfMatchChip(rs.getInt("ifMatch"));
		    	goods.setProducerAddress(rs.getString("ProductAddress"));
		    	goods.setDateOfManufacture(rs.getTimestamp("DateOfManufacture"));
		    	goods.setDateOfStock(rs.getTimestamp("DateOfStock"));
		    	goodsList.add(goods);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return goodsList;
	}
	
	public List<GoodsType> getAllGoodsType(){
		List<GoodsType> goodsTypeList = new ArrayList<>();
		
		ResultSet rs;
		ResultSetMetaData md;		
		String sql = "select * from Goods";
		PreparedStatement pstmt=null;
		try{
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		    md = rs.getMetaData();
		    while (rs.next()) {
		    	GoodsType goodstype = new GoodsType();
		    	goodstype.setTypeId(rs.getInt("GoodsTypeID"));
		    	goodstype.setTypeName(rs.getString("TypeName"));	    	
		    	goodsTypeList.add(goodstype);
		    }
		    rs.close();
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return goodsTypeList;
	}
	
	public int GetAdmin_ID_Password(int adminid,String InputPassword) {// 登陆判断  返回1登陆成功，0失败
		 String password = null;		
		try {
			String strsql = "select password from Administrator where adminid=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, adminid);
			ResultSet rs = psql.executeQuery();
			while (rs.next()) {
			password = rs.getString("password");
			}
			rs.close();
			psql.close();
			if(password.equals(InputPassword)) 				
				return 1;			
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
	
	public String GetPermission(int ID) {//获取权限页面
		String PermissionName = null;
		try {
			String strsql = "select PermissionName from Permission where ID=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, ID);
			ResultSet rs = psql.executeQuery();
			while (rs.next()) {
		    PermissionName = rs.getString("PermissionName");
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
		return PermissionName;

	}
	
	public String GetRole(int RoleID) {//获取role
		String RoleName  = null;
		try {
			String strsql = "select RoleName from Role where RoleID=?";
			PreparedStatement psql = con.prepareStatement(strsql);
			psql.setInt(1, RoleID);
			ResultSet rs = psql.executeQuery();
			while (rs.next()) {
				// 获取这列数据
				RoleName  = rs.getString("RoleName ");
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
		return RoleName;

	}
	
	
public float GetSales(String time) {//获取销售额  还没有写完
		
		String CreateTime=null;
		float PriceInOneOrder=0;
		float TotalPrice=0;
		try {
			String strsql = "select * from orders";
			PreparedStatement psql = con.prepareStatement(strsql);
			
			ResultSet rs = psql.executeQuery();
			while (rs.next()) {
				// 获取这列数据
				CreateTime=rs.getString("CreateTime");
				PriceInOneOrder  = rs.getFloat("TotalPrice");
				if(true) {//按一年
				  TotalPrice=TotalPrice+PriceInOneOrder;
				}
				if(true) {//按月
				  TotalPrice=TotalPrice+PriceInOneOrder;
				}
				
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
		return TotalPrice;

	}

public List<Order> GetOrdersByOrderIdOrMachineNum(long OrderID,int machineNum,int m) {
	List<Order> list=new ArrayList();
	String strsql=null;
	PreparedStatement pstmt=null;
	ResultSet rs =  null;
	
	try {
		if(m==0) {//按orderid查找
			strsql = "select * from Orders where OrderID="+OrderID;
			pstmt = (PreparedStatement)con.prepareStatement(strsql);
			rs = pstmt.executeQuery();
		    while (rs.next()) {
		    	Order order = new Order();
		    	order.setId(rs.getLong("OrderID"));
		    	order.setCreateTime(rs.getTimestamp("CreateTime"));
		    	order.setMachineNum(rs.getInt("MachineNum"));
		    	order.setPrice(rs.getFloat("TotalPrice"));
		    	order.setTotalNum(rs.getInt("TotalNum"));
		    	//System.out.println(rs.getLong("OrderID"));
		    	list.add(order);
		    }
		    rs.close();
			pstmt.close();
            
		}
		if(m==1) {//按机器号查找
			strsql = "select * from orders where MachineNum="+machineNum;
			pstmt = (PreparedStatement)con.prepareStatement(strsql);
			rs = pstmt.executeQuery();
		    while (rs.next()) {
		    	Order order = new Order();
		    	order.setId(rs.getLong("OrderID"));
		    	order.setCreateTime(rs.getTimestamp("CreateTime"));
		    	order.setMachineNum(rs.getInt("MachineNum"));
		    	order.setPrice(rs.getFloat("TotalPrice"));
		    	order.setTotalNum(rs.getInt("TotalNum"));
		    	list.add(order);
		    }
		    rs.close();
			pstmt.close();
		}
	} catch (SQLException e) {
		// 数据库连接失败异常处理
		e.printStackTrace();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return list;
}

public List<GoodsInOneOrder> GetGoodsInfoInOneOrder(long OrderID) throws SQLException {
	List<GoodsInOneOrder> list=new ArrayList();
	String strsql=null;
	PreparedStatement pstmt=null;
	ResultSet rs =  null;
	strsql = "select * from GoodsInOneOrder where OrderID="+OrderID;
	pstmt = (PreparedStatement)con.prepareStatement(strsql);
	rs = pstmt.executeQuery();
	List<List<String>> goods = new ArrayList<>();
    while (rs.next()) {
    	List<String> info = new ArrayList<>();
    	info.add(rs.getString("OrderID"));
    	info.add(rs.getString("GoodsID"));
    	info.add(rs.getString("Num"));
    	//System.out.println(rs.getString("GoodsID"));
    	goods.add(info);
    }
    
    if(goods!=null){
    	for(int i=0;i<goods.size();i++){
    		strsql = "select * from Goods where GoodsID="+goods.get(i).get(1);
    		pstmt = (PreparedStatement)con.prepareStatement(strsql);
    		rs = pstmt.executeQuery();
    		while (rs.next()) {
    			GoodsInOneOrder goodsinfo = new GoodsInOneOrder();
    			goodsinfo.setId(rs.getInt("GoodsID"));
    			goodsinfo.setType(rs.getInt("TypeID"));
    			goodsinfo.setGoodsName(rs.getString("GoodsName"));
    			goodsinfo.setSinglePrice(rs.getFloat("Price"));
    			goodsinfo.setTotalPrice(rs.getFloat("Price")*rs.getInt("Num"));
    			int num =0;
    			if(goods.get(i).get(2)!=null)
    				num=Integer.parseInt(goods.get(i).get(2));
    			goodsinfo.setTotalNum(num);
    			goodsinfo.setUrl(rs.getString("PicUrl"));
    			goodsinfo.setIfMatch(rs.getInt("ifMatch"));
    			goodsinfo.setProducerAddress(rs.getString("ProductAddress"));
    			goodsinfo.setDateOfManufacture(rs.getTimestamp("DateOfManufacture"));
		    	goodsinfo.setDateOfStock(rs.getTimestamp("DateOfStock"));
		    	System.out.println(goodsinfo.getId()+" "+goodsinfo.getGoodsName()+" "+goodsinfo.getProducerAddress()+" "+goodsinfo.getSinglePrice());
		    	list.add(goodsinfo);
    	    }
    	}
    }
    
    rs.close();
	pstmt.close();	
	
	return list;
}


public List GetAllGoods() {//有问题的  可以用数组
	List list=new ArrayList();
	try {
		String strsql = "select * from goods";
		PreparedStatement psql = con.prepareStatement(strsql);
		ResultSet rs = psql.executeQuery();
		long id = 0, typeid = 0, num = 0;
		String goodsname, address, date1, date2,PicUrl;
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
			PicUrl=rs.getString("PicUrl");
			list.add(0,rs.getInt("GoodsID"));
			list.add(1,rs.getInt("TypeID"));
			list.add(2,rs.getString("GoodsName"));
			list.add(3,rs.getFloat("Price"));
			list.add(4,rs.getString("ProductAddress"));
			list.add(5,rs.getInt("Number"));
			list.add(6,rs.getString("DateOfManufacture"));
			list.add(7,rs.getString("DateOfStock"));
			list.add(8,rs.getString("PicUrl"));
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
	return list;
}


public Goods GetGoodsById(long GoodsID) {
	Goods goods = new Goods();
	try {
		String strsql = "select * from goods where GoodsID="+GoodsID;
		PreparedStatement psql = con.prepareStatement(strsql);
		ResultSet rs = psql.executeQuery();

		while (rs.next()) {
			// 获取这列数据
	    	goods.setId(rs.getInt("GoodsID"));
	    	goods.setType(rs.getInt("TypeID"));
	    	goods.setGoodsName(rs.getString("GoodsName"));
	    	goods.setPrice(rs.getFloat("Price"));
	    	goods.setNum(rs.getInt("Num"));
	    	goods.setPicUrl(rs.getString("PicUrl"));
	    	goods.setIfMatchChip(rs.getInt("ifMatch"));
	    	goods.setProducerAddress(rs.getString("ProductAddress"));
	    	goods.setDateOfManufacture(rs.getTimestamp("DateOfManufacture"));
	    	goods.setDateOfStock(rs.getTimestamp("DateOfStock"));
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
	return goods;
}

public Goods GetGoodsByChipId(String chipID) {
	Goods goods = new Goods();
	try {
		String strsql = "select GoodsID from Chips where chipID='"+chipID+"'";
		PreparedStatement psql = con.prepareStatement(strsql);
		ResultSet rs = psql.executeQuery();
        long goodsId = -1;
		while (rs.next()) {
			goodsId = rs.getLong("GoodsID");
		}
		if(goodsId!=-1){
			strsql="select * from goods where GoodsID="+goodsId;
			psql = con.prepareStatement(strsql);
			rs = psql.executeQuery();
			while (rs.next()) {
				goods.setId(rs.getInt("GoodsID"));
		    	goods.setType(rs.getInt("TypeID"));
		    	goods.setGoodsName(rs.getString("GoodsName"));
		    	goods.setPrice(rs.getFloat("Price"));
		    	goods.setNum(rs.getInt("Num"));
		    	goods.setPicUrl(rs.getString("PicUrl"));
		    	goods.setIfMatchChip(rs.getInt("ifMatch"));
		    	goods.setProducerAddress(rs.getString("ProductAddress"));
		    	goods.setDateOfManufacture(rs.getTimestamp("DateOfManufacture"));
		    	goods.setDateOfStock(rs.getTimestamp("DateOfStock"));
			}
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
	return goods;
}
}
