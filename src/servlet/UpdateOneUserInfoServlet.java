package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class UpdateOneUserInfoServlet
 */
@WebServlet("/UpdateOneUserInfoServlet")
public class UpdateOneUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOneUserInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String strRole = request.getParameter("role");
		int role = 0;
		if(strRole != null&& strRole != ""){
			role = Integer.parseInt(request.getParameter("role"));
		}
		String password = request.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setRole(role);
		user.setTel(tel);
		user.setPassword(password);
		
		/*
		 * update user info from database,if finish,set msg to yes,or set it to no
		 *
		 */
		String msg = "yes";
		
		//生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
          
        stringer.array();    
        stringer.object().  
        key("msg").value(msg).   
        endObject();
          
        stringer.endArray();  
        object.element("res", stringer.toString());     
       
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
