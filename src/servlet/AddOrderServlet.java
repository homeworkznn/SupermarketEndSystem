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
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        String id = request.getParameter("createTime");
        String machineNum = request.getParameter("machineNum");
        String price = request.getParameter("price");
        String totalNum = request.getParameter("totalNum");
		String[] goodsIdList=request.getParameterValues("goodsIdList");   //s:["4","5","6"]
		String[] goodsNumList=request.getParameterValues("goodsNumList");
		int[] goodsNum=new int[goodsNumList.length];
		for(int i=0;i<goodsNumList.length;i++){
			goodsNum[i]=Integer.parseInt(goodsNumList[i]);      //goodsNum:[4,5,6] 
		}
		
		/*
		 * insert order into database,if finish,set msg to yes
		 * 
		 */
        String msg = "yes";
        
        //生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        try {    
        	stringer.array();    
            stringer.object().  
            key("msg").value(msg).   
            endObject();
            stringer.endArray();  
            object.element("res", stringer.toString());
            
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
