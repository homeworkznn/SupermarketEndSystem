package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Goods;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetGoodsInfoByChipIdServlet
 */
@WebServlet("/GetGoodsInfoByChipIdServlet")
public class GetGoodsInfoByChipIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGoodsInfoByChipIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8");  
        
        String chipId = request.getParameter("chipId");
        
        String string = "2016-10-24 21:59:06";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        /*
         * get goods info by chipId,set msg=yes if finish
         */
        String msg = "yes";
        
        Goods goods = new Goods();		
		try {
			goods.setId(1);
	        goods.setPrice((float)23.23);
	        goods.setGoodsName("果汁");
	        goods.setProducerAddress("dsdsd");
	        goods.setType(2);
			goods.setDateOfManufacture(sdf.parse(string));
			goods.setDateOfStock(sdf.parse(string));
			goods.setPicUrl("http://ffff.com");
			
			//生成JSON数据  
	         JSONStringer stringer = new JSONStringer();     
	         JSONObject object = new JSONObject();  
	           
	         stringer.array();  
	         stringer.object().  
	         key("msg").value(msg).   
	         endObject();
	         
             stringer.object().  
             key("id").value(goods.id).   
             key("type").value(goods.type).
             key("goodsName").value(goods.goodsName).
             key("price").value(goods.price).
             key("producerAddress").value(goods.producerAddress).
             key("dateOfManufacture").value(goods.dateOfManufacture).  
             key("dateOfStock").value(goods.dateOfStock).
             key("picUrl").value(goods.picUrl).
             endObject();
               
             stringer.endArray();  
             object.element("res", stringer.toString());     
	        
	         response.getOutputStream().write(object.toString().getBytes("UTF-8"));    
	         response.setContentType("text/json; charset=UTF-8"); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
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
