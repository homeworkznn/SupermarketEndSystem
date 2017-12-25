package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Goods;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetAllGoodsServlet
 */
@WebServlet("/GetAllGoodsServlet")
public class GetAllGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
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
          * select all goods info 
          */
         String msg = "yes";
         
         try {
        	 List<Goods> goodsList = new ArrayList<>();
             Goods goods = new Goods();
             goods.setId(1);
             goods.setPrice((float)23.23);
             goods.setGoodsName("果汁");
             goods.setProducerAddress("dsdsd");
             goods.setType(2);
			 goods.setDateOfManufacture(sdf.parse(string));
			 goods.setDateOfStock(sdf.parse(string));
			 goods.setChipId("Ajj2345");
	         goodsList.add(goods);
	         
	       //生成JSON数据  
	         JSONStringer stringer = new JSONStringer();     
	         JSONObject object = new JSONObject();  
	         try {    
	             stringer.array();    
	             stringer.object().  
	             key("msg").value(msg).   
	             endObject();
	             
	             for(Goods g : goodsList) {     
	                 stringer.object().  
	                 key("id").value(g.id).   
	                 key("type").value(g.type).
	                 key("goodsName").value(g.goodsName).
	                 key("price").value(g.price).
	                 key("chipId").value(g.chipId).
	                 key("producerAddress").value(g.producerAddress).
	                 key("dateOfManufacture").value(g.dateOfManufacture).  
	                 key("dateOfStock").value(g.dateOfStock).
	                 endObject();    
	             }    
	             stringer.endArray();  
	             object.element("res", stringer.toString());  
	         } catch (Exception e) {    
	             e.printStackTrace();    
	         }  
	         
	         response.getOutputStream().write(object.toString().getBytes("UTF-8"));    
	         response.setContentType("text/json; charset=UTF-8"); 
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
