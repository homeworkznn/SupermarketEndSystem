package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.AddToMysql;
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
        
        String strGoodsId = request.getParameter("goodsId");
		String strTypeId = request.getParameter("typeId");
		String goodsName = request.getParameter("goodsName");
		String strPrice = request.getParameter("price");
		String strNum = request.getParameter("num");
		String producerAddress = request.getParameter("producerAddress");
		String strDateOfManufacture = request.getParameter("dateOfManufacture");
		String strDateOfStock = request.getParameter("dateOfStock");
		int typeId=0;
		if(strTypeId != null&& strTypeId != ""){
			typeId = Integer.parseInt(strTypeId);
		}
		int num=0;
		if(strNum != null&& strNum != ""){
			num = Integer.parseInt(strNum);
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
		long goodsId=0;
		if(strGoodsId != null&& strGoodsId != ""){
			goodsId = Long.parseLong(strGoodsId);
		}
		
		/*
		 * insert new goods into database,if finish,set msg to yes
		 *
		 */
		AddToMysql addmysql =new AddToMysql();
		int result=addmysql.addGoods(goodsId, typeId, goodsName, price, producerAddress, num,dateOfManufacture, dateOfStock);
		//int result=addmysql.addGoods(112222222, 1, "ceshi", (float) 12.12, "222", 2,dateOfManufacture, dateOfStock);
		String msg = "yes";
		if(result==0){
			msg = "error";
		}
		//生成JSON数据     
        JSONObject object = new JSONObject();     
        object.element("msg", msg);     
       
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
