package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectToMysql {
	 private Connection con;
	 public  ConnectToMysql() {
		  //声明Connection对象	  
		    //驱动程序名
		    String driver = "com.mysql.jdbc.Driver";
		    //URL指向要访问的数据库名OrderingSystem
		    String url = "jdbc:mysql://127.0.0.1:3306/db_supermarketsystem?useSSL=true";
		  
		    //MySQL配置时的用户名
		   String user = "root";
		   //MySQL配置时的密码
		    String password = "root";
		    try {
		    Class.forName(driver);
		    //1.getConnection()方法，连接MySQL数据库！！
		    con = DriverManager.getConnection(url,user,password);
		    con.setAutoCommit(false); 
		    }catch(ClassNotFoundException e) {   
	              //数据库驱动类异常处理
	              System.out.println("Sorry,can`t find the Driver!");   
	              e.printStackTrace();   
	             } catch(SQLException e) {
	              //数据库连接失败异常处理
	              e.printStackTrace();  
	              }catch (Exception e) {
	              // TODO: handle exception
	              e.printStackTrace();
	          }
	 }
	public Connection getcon() {
		return con;		
	}
	public Connection setcon(Connection con) {
		return this.con=con;		
	}

 }
