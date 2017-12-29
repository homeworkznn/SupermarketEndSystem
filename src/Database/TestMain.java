package Database;

import java.io.IOException;
import java.util.Scanner;

public class TestMain {
	static GetDataFromMySql mysql = new GetDataFromMySql();
	static AddToMysql addmysql =new AddToMysql();
	static DeleteFromMysql deletemysql = new DeleteFromMysql();
	static DisplayFromMysql displaymysql =new DisplayFromMysql();
	static UpdateToMysql updatemysql=new UpdateToMysql();
	static int adminid=0;
	public static void main(String[] args) {
		
		
		displaymysql.displayGoods();
		
		/*String password=null;
		Scanner sc = new Scanner(System.in);
		System.out.println("1、扫描商品");
		System.out.println("2、管理员端");
		System.out.println("请输入你的操作号：");
		int Input = sc.nextInt();
		if(Input==1) {//扫描支付商品
			
		}
		if(Input==2) {//管理员端
			System.out.println("*****管理员端********");
			System.out.println("请输入管理账号：（）");
			int Input_adminid = sc.nextInt();
			System.out.println("请输入密码");
			String Input_password = sc.next();
			password=mysql.selectAdmin_ID_Password(Input_adminid);
			if(Input_password.equals(password)) {//登陆成功
				System.out.println("登陆成功");
				*/
				/*long OrderID=mysql.AutoGetordernumber();
				String CreateTime=null;
				int MachineNum=2;
				float TotalPrice=0;
				int TotalNum=100;
				mysql.addOrders(OrderID, CreateTime, MachineNum, TotalPrice, TotalNum);
				mysql.displayOrders();*/
		        //mysql.displayGoods();
				
			/*}else if(password==null) {
				System.out.println("你输入的账号不存在");
			}else {
				System.out.println("你输入的密码不正确");
			}
		}
		
	*/	
	}
}
