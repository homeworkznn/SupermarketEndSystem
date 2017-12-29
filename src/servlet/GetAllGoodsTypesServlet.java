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
import model.GoodsType;
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetAllGoodsTypesServlet
 */
@WebServlet("/GetAllGoodsTypesServlet")
public class GetAllGoodsTypesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllGoodsTypesServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		/*
		 * select all goods types in the database
		 */
        String msg = "yes";
        
        GetDataFromMySql getData = new GetDataFromMySql();
        List<GoodsType> list = new ArrayList<>();
        list=getData.getAllGoodsType();
        //生成JSON数据      
        JSONObject object = new JSONObject();  
        object.put("msg", msg);
        try {      
        	int i=0;
        	JSONObject j0  = new JSONObject();
             for(GoodsType g : list) {   
            	 JSONObject j1  = new JSONObject();
            	 j1.put("id", g.typeId);
            	 j1.put("name", g.typeName);
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
