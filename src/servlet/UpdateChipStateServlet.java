package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Chip;
import Database.UpdateToMysql;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class UpdateChipStateServlet
 */
@WebServlet("/UpdateChipStateServlet")
public class UpdateChipStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateChipStateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		String id = request.getParameter("chipId");
		String strState = request.getParameter("state");
		UpdateToMysql update = new UpdateToMysql();
		/*
		 *update chip's state into database,if finish,set msg to yes
		 *
		 */
		String msg = "yes";
		int state = 0;
		int result = 0;
		if(strState != null&& strState != ""){
			state = Integer.parseInt(strState);
			result=update.UpdateChipsState(id,state);
		}else{
			msg="error";
		}
		if(result==0){
			msg="error";
		}
		//生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
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
