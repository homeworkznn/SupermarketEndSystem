package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DeleteFromMysql;
import model.User;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class DeleteUserByIdServlet
 */
@WebServlet("/DeleteUserByIdServlet")
public class DeleteUserByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserByIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        DeleteFromMysql delete = new DeleteFromMysql();
		String strId = request.getParameter("userId");
		int id = 0;
		int result = 1;
		/*
		 * delete user from database by roleId,if finish,set msg to yes
		 *
		 */	
		String msg = "yes";
		if(strId!=null&&strId!=""){
			id = Integer.parseInt("strId");
			result=delete.deleteAdmin(id);
		}
		if(result==0)
			msg="error";
		
        
        //生成JSON数据     
        JSONObject object = new JSONObject();  
        try {         	 
            object.put("msg", msg);
            
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
