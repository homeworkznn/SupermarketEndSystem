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
 * Servlet implementation class GetAnnualTurnoverServlet
 */
@WebServlet("/GetAnnualTurnoverServlet")
public class GetAnnualTurnoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAnnualTurnoverServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8");   
        
        String strSortType = request.getParameter("sortType");
        String strYear = request.getParameter("year");
        
        String sortType = "desc";
        if(strSortType!=null && strSortType !="")
        	sortType = strSortType;
	
        /*
         * select goods by sortType:asc or desc
         */
       
        String msg = "yes";
        
        List<AnnualTurnoverOfOneGoods> goodsTurnoverList = new ArrayList<>();
  	    AnnualTurnoverOfOneGoods goodsTurnover = new AnnualTurnoverOfOneGoods();
  	    goodsTurnover.setGoodsType(1);
  	    goodsTurnover.setGoodsTypeName("百事可乐");
  	    goodsTurnover.setGoodsId(1);
  	    goodsTurnover.setGoodsName("百事可乐123ml");
  	    goodsTurnover.setAnnualAverageUnitPrice((float)12.12);
  	    goodsTurnover.setAnnualTurnover(1222);
  	    goodsTurnover.setAnnualIncome((float)34.34);
  	    goodsTurnover.setYear("2018");
  	    goodsTurnoverList.add(goodsTurnover);
        
      //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        object.put("msg", msg);
        try {    
            stringer.array();                   
            for(AnnualTurnoverOfOneGoods g : goodsTurnoverList) {     
                stringer.object().  
                key("goodsType").value(g.goodsType).   
                key("goodsName").value(g.goodsName).
                key("goodsId").value(g.goodsId).
                key("annualAverageUnitPrice").value(g.annualAverageUnitPrice).
                key("annualTurnover").value(g.annualTurnover).  
                key("annualIncome").value(g.annualIncome).
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
