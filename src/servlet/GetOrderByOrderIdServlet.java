package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GoodsInOneOrder;
import model.Order;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetOrderByOrderIdServlet
 */
@WebServlet("/GetOrderByOrderIdServlet")
public class GetOrderByOrderIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderByOrderIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8");   
        
		String string = "2016-10-24 21:59:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		/*
		 * select order info from database by orderId
		 */
        String msg = "yes";
      
		Order order = new Order();
		order.setId(1);
		order.setMachineNum(0);
		order.setPrice((float)123.12);
		order.setTotalNum(2);
		try {
			order.setCreateTime(sdf.parse(string));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<GoodsInOneOrder> goodsList = new ArrayList<>();
		GoodsInOneOrder goods = new GoodsInOneOrder();
		for(int i=0;i<2;i++){
			goods = new GoodsInOneOrder();
			goods.setId(1);
			goods.setGoodsName("百事可乐123ml");
			goods.setType(1);
			goods.setSinglePrice((float)232.22);
			goods.setTotalPrice((float)344.33);
			goods.setProducerAddress("hhh");
			goods.setTotalNum(2);
			try {
				goods.setDateOfManufacture(sdf.parse(string));
				goods.setDateOfStock(sdf.parse(string));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			goodsList.add(goods);
		}
		order.setGoodsInOneOrder(goodsList);
		
		//生成JSON数据      
        JSONObject object = new JSONObject(); 
        object.put("msg", msg);
        try {    
            JSONObject j0  = new JSONObject();
        	j0.put("orderId", order.id);
        	j0.put("createTime", order.createTime);
        	j0.put("machineNum", order.machineNum);
        	j0.put("price", order.price);
        	j0.put("totalNum", order.totalNum);
        	JSONObject j2  = new JSONObject();
        	for(int j=0;j<order.goodsInOneOrder.size();j++){
        		JSONObject j1  = new JSONObject();
        		j1.put("goodsId", order.getGoodsInOneOrder().get(j).id);
        		j1.put("goodsName", order.getGoodsInOneOrder().get(j).goodsName);
        		j1.put("type", order.getGoodsInOneOrder().get(j).type);
        		j1.put("totalNum", order.getGoodsInOneOrder().get(j).totalNum);
        		j1.put("singlePrice", order.getGoodsInOneOrder().get(j).singlePrice);
        		j1.put("totalPrice", order.getGoodsInOneOrder().get(j).totalPrice);
        		j1.put("producerAddress", order.getGoodsInOneOrder().get(j).producerAddress);
        		j1.put("dateOfManufacture", order.getGoodsInOneOrder().get(j).dateOfManufacture);
        		j1.put("dateOfStock", order.getGoodsInOneOrder().get(j).dateOfStock);
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
		doGet(request, response);
	}

}
