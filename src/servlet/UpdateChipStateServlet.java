package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Chip;
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
		
		int state = 0;
		if(strState != null&& strState != ""){
			state = Integer.parseInt(strState);
		}
			
		/*
		 *update chip's state into database,if finish,set msg to yes
		 *
		 */
		String msg = "yes";
		
		//����JSON����  
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
		doGet(request, response);
	}

}
