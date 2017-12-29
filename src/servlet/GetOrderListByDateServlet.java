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
 * Servlet implementation class GetOrderListByDateServlet
 */
@WebServlet("/GetOrderListByDateServlet")
public class GetOrderListByDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderListByDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8");   
        
        String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		GetDataFromMySql getData =new GetDataFromMySql();
		String string = "2016-10-24 21:59:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String strOrderId = request.getParameter("orderId");
		long orderId =-1;
		List<Order> order = new ArrayList();
		List<GoodsInOneOrder> goodsList = new ArrayList<>();
		/*
		 * select order info from database by orderId
		 */
        String msg = "yes";
        if(strOrderId!=null&&strOrderId!=""){
			orderId = Long.parseLong(strOrderId);
			order = getData.GetOrdersByOrderIdOrMachineNum(12345, -1, 0);
			try {
				goodsList = getData.GetGoodsInfoInOneOrder(12345);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(order==null){
			msg="error";
		}else{
			
		}
		
		//生成JSON数据      
        JSONObject object = new JSONObject(); 
        object.put("msg", msg);
        try {    
            JSONObject j0  = new JSONObject();
        	j0.put("orderId", order.get(0).id);
        	j0.put("createTime", order.get(0).createTime);
        	j0.put("machineNum", order.get(0).machineNum);
        	j0.put("price", order.get(0).price);
        	j0.put("totalNum", order.get(0).totalNum);
        	JSONObject j2  = new JSONObject();
        	for(int j=0;j<goodsList.size();j++){
        		JSONObject j1  = new JSONObject();
        		j1.put("goodsId", goodsList.get(j).getId());
        		j1.put("goodsName", goodsList.get(j).getGoodsName());
        		j1.put("type", goodsList.get(j).getType());
        		j1.put("totalNum", goodsList.get(j).getTotalNum());
        		j1.put("singlePrice", goodsList.get(j).singlePrice);
        		j1.put("totalPrice", goodsList.get(j).getTotalPrice());
        		j1.put("producerAddress", goodsList.get(j).getProducerAddress());
        		j1.put("dateOfManufacture", goodsList.get(j).getDateOfManufacture());
        		j1.put("dateOfStock", goodsList.get(j).getDateOfStock());
        		j1.put("url",goodsList.get(j).getUrl());
        		j1.put("ifMatch",goodsList.get(j).getIfMatch());
        		j2.put("goods"+j, j1);
        	}
        	j0.put("goods", j2);
            
            object.put("res", j0);  
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
