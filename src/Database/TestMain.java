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
		System.out.println("1��ɨ����Ʒ");
		System.out.println("2������Ա��");
		System.out.println("��������Ĳ����ţ�");
		int Input = sc.nextInt();
		if(Input==1) {//ɨ��֧����Ʒ
			
		}
		if(Input==2) {//����Ա��
			System.out.println("*****����Ա��********");
			System.out.println("����������˺ţ�����");
			int Input_adminid = sc.nextInt();
			System.out.println("����������");
			String Input_password = sc.next();
			password=mysql.selectAdmin_ID_Password(Input_adminid);
			if(Input_password.equals(password)) {//��½�ɹ�
				System.out.println("��½�ɹ�");
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
				System.out.println("��������˺Ų�����");
			}else {
				System.out.println("����������벻��ȷ");
			}
		}
		
	*/	
	}
}
