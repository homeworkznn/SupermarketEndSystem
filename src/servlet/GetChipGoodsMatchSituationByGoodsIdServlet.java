package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.GetDataFromMySql;
import model.Chip;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetChipGoodsMatchSituationByGoodsIdServlet
 */
@WebServlet("/GetChipGoodsMatchSituationByGoodsIdServlet")
public class GetChipGoodsMatchSituationByGoodsIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetChipGoodsMatchSituationByGoodsIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		String strGoodsId = request.getParameter("goodsId");
		long goodsId = -1;
		 /*
	     * select matching situation feom database by goodsId,if list not null,set msg=yes
	     */
		GetDataFromMySql getData =new GetDataFromMySql();
        String msg = "yes";
        List<Chip> list = new ArrayList<>();
		//if(strGoodsId!=null&&strGoodsId!=""){
			//goodsId = Long.parseLong(strGoodsId);
			list = getData.getChipsGoodsMatchSituationByGoodsId(2);
		//}
		if(list==null)
			msg="error";
		
		//生成JSON数据      
        JSONObject object = new JSONObject();  
        object.element("msg", msg);
        try {      
        	int i=0;
        	JSONObject j0  = new JSONObject();
             for(Chip a : list) {   
            	 JSONObject j1  = new JSONObject();
            	 j1.put("id", a.id);
            	 j1.put("goodsId", a.goodsId);
            	 j1.put("state", a.state);
            	 j0.put("chip"+i, j1);
            	 i++;
             }  
             object.element("res", j0);  
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
