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
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/AddGoodsServlet")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		String strType = request.getParameter("type");
		String goodsName = request.getParameter("goodsName");
		String strPrice = request.getParameter("price");
		String producerAddress = request.getParameter("producerAddress");
		String strDateOfManufacture = request.getParameter("dateOfManufacture");
		String strDateOfStock = request.getParameter("dateOfStock");
		int type=0;
		if(strType != null&& strType != ""){
			type = Integer.parseInt(strType);
		}
		float price=0;
		if(strPrice != null&& strPrice != ""){
			price = Float.parseFloat(strPrice);
		}
		String dateOfManufacture = "2000-12-12 00:00:00";
		if(strDateOfManufacture != null&& strDateOfManufacture != ""){
			dateOfManufacture = strDateOfManufacture;
		}
		String dateOfStock = "2000-12-12 00:00:00";
		if(strDateOfStock != null&& strDateOfStock != ""){
			dateOfStock = strDateOfStock;
		}
		
		Goods goods = new Goods();
		goods.setType(type);
		goods.setPrice(price);
		goods.setProducerAddress(producerAddress);
		goods.setGoodsName(goodsName);
		try {
			goods.setDateOfManufacture(sdf.parse(dateOfManufacture));
			goods.setDateOfStock(sdf.parse(dateOfStock));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * insert new goods into database,if finish,set msg to yes
		 *
		 */
		String msg = "yes";
		
		//生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
          
        stringer.array();    
        stringer.object().  
        key("msg").value(msg).   
        endObject();
          
        stringer.endArray();  
        object.element("res", stringer.toString());     
       
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
