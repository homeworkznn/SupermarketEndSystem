package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Goods;
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class GetUserByRoleIdServlet
 */
@WebServlet("/GetUserByRoleIdServlet")
public class GetUserByRoleIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserByRoleIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		String roleId = request.getParameter("roleId");
		/*
		 * select user from database by roleId,if finish,set msg to yes
		 *
		 */
		List<User> userList = new ArrayList<>();
		User user = new User();
		user.setName("gg");
		user.setRole(1);
		user.setTel("123456");
		user.setPassword("12345678");
		userList.add(user);
		
		String msg = "yes";
        
        //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {    
        	stringer.array();    
            stringer.object().  
            key("msg").value(msg).   
            endObject();
            
            try {      
	             for(User u : userList) {     
	            	 stringer.object().  
	                 key("id").value(u.id).   
	                 key("name").value(u.name).
	                 key("role").value(u.role).
	                 key("tel").value(u.tel).
	                 key("password").value(u.password).
	                 endObject();
	             }    
	             stringer.endArray();  
	             object.element("res", stringer.toString());  
	         } catch (Exception e) {    
	             e.printStackTrace();    
	         }    
            
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
