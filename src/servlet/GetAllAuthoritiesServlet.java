package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Authority;
import model.GoodsType;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetAllAuthoritiesServlet
 */
@WebServlet("/GetAllAuthoritiesServlet")
public class GetAllAuthoritiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllAuthoritiesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		/*
		 * select all authorities in the database
		 */
        String msg = "yes";
        
        List<Authority> list = new ArrayList<>();
        Authority authority = new Authority();
        authority.setId(0);
        authority.setLayoutName("Login");
        list.add(authority);
        
        //生成JSON数据      
        JSONObject object = new JSONObject();  
        object.element("msg", msg);
        try {      
        	int i=0;
        	JSONObject j0  = new JSONObject();
             for(Authority a : list) {   
            	 JSONObject j1  = new JSONObject();
            	 j1.put("id", a.id);
            	 j1.put("layoutName", a.layoutName);
            	 j0.put("authority"+i, j1);
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
