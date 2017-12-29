package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.UpdateToMysql;
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
        
        String strId = request.getParameter("id");
		String name = request.getParameter("name");
		String strTel = request.getParameter("tel");
		String strRole = request.getParameter("roleId");
		String password = request.getParameter("password");
		UpdateToMysql update = new UpdateToMysql();
		int role = 0;
		if(strRole != null&& strRole != ""){
			role = Integer.parseInt(request.getParameter("role"));
		}
		int id = 0;
		if(strId != null&& strId != ""){
			id = Integer.parseInt(strId);
		}
		long tel = 0;
		if(strTel != null&& strTel != ""){
			tel = Long.parseLong(strTel);
		}
		User user = new User();
		user.setName(name);
		user.setRole(role);
		user.setTel(tel);
		user.setPassword(password);
		user.setId(id);
		/*
		 * update user info from database,if finish,set msg to yes,or set it to no
		 *
		 */
		int result=update.UpdateUserInfoById(user);
		String msg = "yes";
		if(result==0)
			msg="error";
		
		//生成JSON数据     
        JSONObject object = new JSONObject();  
        object.put("msg", msg); 
       
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
