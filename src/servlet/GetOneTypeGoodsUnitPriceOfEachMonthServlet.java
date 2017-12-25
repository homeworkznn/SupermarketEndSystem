package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AnnualTurnoverOfOneGoods;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetOneTypeGoodsUnitPriceOfEachMonthServlet
 */
@WebServlet("/GetOneTypeGoodsUnitPriceOfEachMonthServlet")
public class GetOneTypeGoodsUnitPriceOfEachMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneTypeGoodsUnitPriceOfEachMonthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8");   
        
        String strGoodsTypeId = request.getParameter("goodsTypeId");
        String strYear = request.getParameter("year");
        
        int goodsTypeId = -1;
        if(strGoodsTypeId!=null && strGoodsTypeId !="")
        	goodsTypeId = Integer.parseInt(strGoodsTypeId);
	
        /*
         * select goods's unit price of each month during strYear
         */
        String msg = "yes";
        
        List<String> unitPriceList = new ArrayList<>();
        unitPriceList.add("12");
        unitPriceList.add("12");
        unitPriceList.add("12");
        unitPriceList.add("12");
        unitPriceList.add("12");
        
      //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {    
            stringer.array();    
            
            stringer.object().  
            key("msg").value(msg).   
            endObject();
            
            for(int i=0;i<unitPriceList.size();i++) {     
                stringer.object().  
                key((i+1)+"").value(unitPriceList.get(i)).
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
