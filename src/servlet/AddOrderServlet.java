package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.AddToMysql;
import Database.GetDataFromMySql;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        AddToMysql addmysql =new AddToMysql();
        GetDataFromMySql getData = new GetDataFromMySql();
        
        String createTime = request.getParameter("createTime");
        String strMachineNum = request.getParameter("machineNum");
        String price = request.getParameter("price");
        String strTotalNum = request.getParameter("totalNum");
		String[] goodsIdList=request.getParameterValues("goodsIdList");   //s:["4","5","6"]
		String[] goodsNumList=request.getParameterValues("goodsNumList");
		//String[] goodsTypeList=request.getParameterValues("goodsTypeList");
		int machineNum = 0;
		if(strMachineNum!=null&&strMachineNum!="")
			machineNum=Integer.parseInt(strMachineNum);
		int totalNum = 0;
		if(strTotalNum!=null&&strTotalNum!="")
			totalNum=Integer.parseInt(strTotalNum);
		float totalPrice = 0;
		if(price!=null&&price!="")
			totalPrice=Float.parseFloat(price);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		createTime=df.format(new Date());
		/*
		 * insert order into database,if finish,set msg to yes
		 * 
		 */
		long orderId=getData.AutoGetordernumber();
		//int result1=addmysql.addOrders(orderId,createTime, machineNum, totalPrice, totalNum);
		int result1=addmysql.addOrders(orderId,createTime, 1, (float)12.12, 2);
        String msg = "yes";
        if(result1==0){
			msg = "error";
		}
        if(goodsNumList!=null&&goodsIdList!=null){
        	int[] goodsNum=new int[goodsNumList.length];
    		for(int i=0;i<goodsNumList.length;i++){
    			goodsNum[i]=Integer.parseInt(goodsNumList[i]);      //goodsNum:[4,5,6] 
    		}
    		long[] goodsId=new long[goodsIdList.length];
    		for(int i=0;i<goodsIdList.length;i++){
    			goodsId[i]=Integer.parseInt(goodsIdList[i]);      //goodsNum:[4,5,6] 
    		}
            //for(int i=0;i<goodsNum.length;i++){
            	//result1=addmysql.addGoodsInOneOrder(orderId, goodsId[i], goodsNum[i]);
            	result1=addmysql.addGoodsInOneOrder(orderId, 233, 4);
            	if(result1==0){
        			msg = "error";
        		}
           // }
        }
        
        //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {         	
            object.put("msg", msg);
            
            response.getOutputStream().write(object.toString().getBytes("UTF-8"));    
            response.setContentType("text/json; charset=UTF-8"); 
        } catch (Exception e) {    
            e.printStackTrace();    
        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
