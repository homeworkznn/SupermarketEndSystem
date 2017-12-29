package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectToMysql {
	 private Connection con;
	 public  ConnectToMysql() {
		  //����Connection����	  
		    //����������
		    String driver = "com.mysql.jdbc.Driver";
		    //URLָ��Ҫ���ʵ����ݿ���OrderingSystem
		    String url = "jdbc:mysql://127.0.0.1:3306/db_supermarketsystem?useSSL=true";
		  
		    //MySQL����ʱ���û���
		   String user = "root";
		   //MySQL����ʱ������
		    String password = "root";
		    try {
		    Class.forName(driver);
		    //1.getConnection()����������MySQL���ݿ⣡��
		    con = DriverManager.getConnection(url,user,password);
		    con.setAutoCommit(false); 
		    }catch(ClassNotFoundException e) {   
	              //���ݿ��������쳣����
	              System.out.println("Sorry,can`t find the Driver!");   
	              e.printStackTrace();   
	             } catch(SQLException e) {
	              //���ݿ�����ʧ���쳣����
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
