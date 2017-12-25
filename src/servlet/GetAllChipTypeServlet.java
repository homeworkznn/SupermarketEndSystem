package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GoodsType;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetAllChipTypeServlet
 */
@WebServlet("/GetAllChipTypeServlet")
public class GetAllChipTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllChipTypeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		/*
		 * select all chip types in the database
		 */
        String msg = "yes";
        
        List<List<String>> chiptypelist = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("A");  //chiptype
        list.add("goodsTypeId");
        list.add("goodsTypeName");
        chiptypelist.add(list);
        
        
        //生成JSON数据      
        JSONObject object = new JSONObject();  
        object.element("msg", msg);
        try {      
        	int i=0;
        	JSONObject j0  = new JSONObject();
             for(List<String> g : chiptypelist) {   
            	 JSONObject j1  = new JSONObject();
            	 j1.put("chipType", g.get(0));
            	 j1.put("goodsTypeId", g.get(1));
            	 j1.put("goodsTypeName", g.get(2));
            	 j0.put("type"+i, j1);
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
		doGet(request, response);
	}

}
