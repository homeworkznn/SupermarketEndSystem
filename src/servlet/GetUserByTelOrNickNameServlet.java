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
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetUserByTelOrNickNameServlet
 */
@WebServlet("/GetUserByTelOrNickNameServlet")
public class GetUserByTelOrNickNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserByTelOrNickNameServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        GetDataFromMySql getData=new GetDataFromMySql();
		String strTel = request.getParameter("tel");
		String name = request.getParameter("name");
		User user = new User();
		List<User> list = new ArrayList<>();
		long tel = 0;
		if(strTel!=null && strTel!=""){
			tel=Long.parseLong(strTel);
			//user=getData.getUserByNameOrTel(tel, name);
		}
		/*
		 * select user from database by nickname or tel
		 *
		 */
		String msg = "yes";
		list = getData.getUserInfoByTelOrName(tel, "hq");
		
      //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {    
        	object.put("msg", msg);    
        	JSONObject j0  = new JSONObject();
        	for(int i=0;i<list.size();i++){
        		JSONObject j1  = new JSONObject();
           	    j1.put("id", user.id);
           	    j1.put("name", user.name);
           	    j1.put("role", user.role);
           	    j1.put("tel", user.tel);
        	    j1.put("password", user.password);
        	    j0.put("user"+i, j1);
        	}
        	
    	    object.put("res", j0);  
            
            response.getOutputStream().write(object.toString().getBytes("UTF-8"));    
            response.setContentType("text/json; charset=UTF-8"); 
        } catch (Exception e) {    
            e.printStackTrace();    
        }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
