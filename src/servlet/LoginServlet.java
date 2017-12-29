package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Database.GetDataFromMySql;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		String strId = request.getParameter("id");
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");
		long id=0;
		/*
		 * login operation,set msg = yes if user message match
		 */
		String msg = "yes";
		GetDataFromMySql get = new GetDataFromMySql();
		if(strId!=null&&strId!=""){		
			id=Long.parseLong(strId);
			String responPassword = get.selectAdmin_ID_Password(id);
			if(password!=responPassword||!password.equals(responPassword)){
				msg="error";
			}
		}
		
		//生成JSON数据      
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
