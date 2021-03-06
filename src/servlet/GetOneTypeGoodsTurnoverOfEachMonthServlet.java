package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetOneGoodsTurnoverOfEachMonthServlet
 */
@WebServlet("/GetOneGoodsTurnoverOfEachMonthServlet")
public class GetOneTypeGoodsTurnoverOfEachMonthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneTypeGoodsTurnoverOfEachMonthServlet() {
        super();
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
         * select goods's turnover of each month during strYear
         */
        String msg = "yes";
        
        List<String> turnoverList = new ArrayList<>();
        turnoverList.add("12");
        turnoverList.add("12");
        turnoverList.add("12");
        turnoverList.add("12");
        turnoverList.add("12");
        
      //����JSON����  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        object.put("msg", msg);
        try {    
            stringer.array();              
            
            for(int i=0;i<turnoverList.size();i++) {     
                stringer.object().  
                key((i+1)+"").value(turnoverList.get(i)).
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
		doGet(request, response);
	}

}
