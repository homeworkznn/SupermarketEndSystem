package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class UpdateGoodsPictureServlet
 */
@WebServlet("/UpdateGoodsPictureServlet")
public class UpdateGoodsPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsPictureServlet() {
        super();     
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		String goodsTypeId = request.getParameter("goodsTypeId");
		String picUrl = request.getParameter("picUrl");
		
		/*
		 * update goods's picture,set msg = yes if user message match
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
