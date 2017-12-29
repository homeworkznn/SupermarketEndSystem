package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.GetDataFromMySql;
import model.GoodsInOneOrder;
import model.Order;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetOrderListByMachineNumServlet
 */
@WebServlet("/GetOrderListByMachineNumServlet")
public class GetOrderListByMachineNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderListByMachineNumServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8");   
        
        String strMachineNum = request.getParameter("machineNum");
        GetDataFromMySql getData =new GetDataFromMySql();
		int machineNum = 0;
		String msg = "yes";
		List<Order> order = new ArrayList();
		List<List<GoodsInOneOrder>> goodsList = new ArrayList<>();
		/*
		 * select order info from database by machineNum
		 */
		if(strMachineNum!=null && strMachineNum !=""){
			machineNum = Integer.parseInt(strMachineNum);
			order = getData.GetOrdersByOrderIdOrMachineNum(machineNum, 1, 1);
			for(int i=0;i<order.size();i++){
				List<GoodsInOneOrder> list = new ArrayList<>();
				try {
					list=getData.GetGoodsInfoInOneOrder(order.get(i).getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				goodsList.add(list);
			}
		}else{
			msg="error";
		}
		if(order==null){
			msg="error";
		}
		
		//生成JSON数据      
        JSONObject object = new JSONObject(); 
        object.put("msg", msg);
        try {  
        	JSONObject jjj  = new JSONObject();
        	for(int k=0;k<order.size();k++){
        		JSONObject j0  = new JSONObject();
            	j0.put("orderId", order.get(k).id);
            	j0.put("createTime", order.get(k).createTime);
            	j0.put("machineNum", order.get(k).machineNum);
            	j0.put("price", order.get(k).price);
            	j0.put("totalNum", order.get(k).totalNum);
            	JSONObject j2  = new JSONObject();
            	for(int j=0;j<goodsList.size();j++){
            		JSONObject j1  = new JSONObject();
            		j1.put("goodsId", goodsList.get(k).get(j).getId());
            		j1.put("goodsName", goodsList.get(k).get(j).getGoodsName());
            		j1.put("type", goodsList.get(k).get(j).getType());
            		j1.put("totalNum", goodsList.get(k).get(j).getTotalNum());
            		j1.put("singlePrice", goodsList.get(k).get(j).singlePrice);
            		j1.put("totalPrice", goodsList.get(k).get(j).getTotalPrice());
            		j1.put("producerAddress", goodsList.get(k).get(j).getProducerAddress());
            		j1.put("dateOfManufacture", goodsList.get(k).get(j).getDateOfManufacture());
            		j1.put("dateOfStock", goodsList.get(k).get(j).getDateOfStock());
            		j1.put("url",goodsList.get(k).get(j).getUrl());
            		j1.put("ifMatch",goodsList.get(k).get(j).getIfMatch());
            		j2.put("goods"+j, j1);
            	}
            	j0.put("goods", j2);
            	jjj.put("order"+k, j0);
        	}
            
            object.put("res", jjj);  
        } catch (Exception e) {    
            e.printStackTrace();    
        }  
        
        response.getOutputStream().write(object.toString().getBytes("UTF-8"));    
        response.setContentType("text/json; charset=UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
