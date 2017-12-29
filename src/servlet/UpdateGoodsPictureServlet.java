package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.UpdateToMysql;
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
        
		String strGoodsId = request.getParameter("goodsId");
		String picUrl = request.getParameter("picUrl");
		UpdateToMysql update = new UpdateToMysql();
		/*
		 * update goods's picture,set msg = yes if user message match
		 */
		String msg = "yes";
		long goodsId = 0;
		int result = 1;
		if(strGoodsId!=null&&strGoodsId!=""){
			goodsId=Long.parseLong(strGoodsId);
			result = update.UpdateGoodsPicture(goodsId, picUrl);
		}else{
			msg="error";
		}
		if(result==0){
			msg="error";
		}
		//生成JSON数据  
        JSONStringer stringer = new JSONStringer();     
        JSONObject object = new JSONObject();  
        object.put("msg", msg);
        
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
