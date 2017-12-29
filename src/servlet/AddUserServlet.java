package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.AddToMysql;
import Database.GetDataFromMySql;
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        AddToMysql addmysql =new AddToMysql();
        GetDataFromMySql getData = new GetDataFromMySql();
		String name = request.getParameter("name");
		String strTel = request.getParameter("tel");
		String strRole = request.getParameter("role");
		int role = 0;
		if(strRole != null&& strRole != ""){
			role = Integer.parseInt(strRole);
		}
		long tel = 0;
		if(strTel != null&& strTel != ""){
			tel = Integer.parseInt(strTel);
		}
		String password = request.getParameter("password");
		
		/*User user = new User();
		user.setName(name);
		user.setRole(role);
		user.setTel(tel);
		user.setPassword(password);*/
		
		/*
		 * insert new user into database,if finish,set msg to yes
		 *
		 */
		int AdminID = (int)getData.AutoGetordernumber();
		int result = addmysql.addAdmin(AdminID, name, role, tel, password);
		//int result = addmysql.addAdmin(AdminID, "hq", 2, 12345, "password");
		String msg = "yes";
		if(result==0){
			msg = "error";
		}
		
		/*
		 * 生成JSON数据    
		 */
        JSONObject object = new JSONObject();  
        object.element("msg", msg);     
       
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
