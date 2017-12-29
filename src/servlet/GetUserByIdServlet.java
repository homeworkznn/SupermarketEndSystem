package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.GetDataFromMySql;
import model.Goods;
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetUserByIdServlet
 */
@WebServlet("/GetUserByIdServlet")
public class GetUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserByIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        GetDataFromMySql getData =new GetDataFromMySql();
		String id = request.getParameter("userId");
		int userId = 0;
		/*
		 * select user from database by id
		 *
		 */
		String msg="yes";
		User user = new User();
		//if(id!=null&&id!=""){
			//userId = Integer.parseInt(id);
			user=getData.getUserInfoById(123);
		//}else{
		//	msg="error";
		//}
		
        
      //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {    
        	object.put("msg", msg);            
        	JSONObject j1  = new JSONObject();
       	    j1.put("id", user.id);
       	    j1.put("name", user.name);
       	    j1.put("role", user.role);
       	    j1.put("tel", user.tel);
    	    j1.put("password", user.password);
    	    object.put("res", j1);  
            
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
