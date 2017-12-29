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

import Database.GetDataFromMySql;
import model.Goods;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetAllNotMatchedGoodsServlet
 */
@WebServlet("/GetAllNotMatchedGoodsServlet")
public class GetAllNotMatchedGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllNotMatchedGoodsServlet() {
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
         * select all goods info which had not matched
         */
        String msg = "yes";
        GetDataFromMySql getData = new GetDataFromMySql();
        List<Goods> goodsList = new ArrayList<>();
        goodsList=getData.getAllNotMatchGoods();
        
      //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        object.put("msg", msg);
        try {    
            stringer.array();    
           
            for(Goods g : goodsList) {     
                stringer.object().  
                key("id").value(g.id).   
                key("type").value(g.type).
                key("goodsName").value(g.goodsName).
                key("price").value(g.price).
                key("num").value(g.num).
                key("url").value(g.picUrl).
                key("ifMatchChip").value(g.ifMatchChip).
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
