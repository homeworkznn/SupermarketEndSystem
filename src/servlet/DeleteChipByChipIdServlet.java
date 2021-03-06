package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.DeleteFromMysql;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class DeleteChipByChipIdServlet
 */
@WebServlet("/DeleteChipByChipIdServlet")
public class DeleteChipByChipIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteChipByChipIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        DeleteFromMysql delete = new DeleteFromMysql();
		String strId = request.getParameter("chipId");
		/*
		 * delete user from database by roleId,if finish,set msg to yes
		 *
		 */	
		String msg = "yes";
		int result = 1;
		if(strId!=null&&strId!=""){		
			result=delete.deletechips(strId);
		}
		if(result == 0){
			msg="error";
		}
		
        
        //����JSON����     
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
