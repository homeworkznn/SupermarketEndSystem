package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.UpdateToMysql;
import model.Chip;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdateGoodsIdOfOneChipServlet
 */
@WebServlet("/UpdateGoodsIdOfOneChipServlet")
public class UpdateGoodsIdOfOneChipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGoodsIdOfOneChipServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
        String chipId = request.getParameter("chipId");
        String strGoodsId = request.getParameter("goodsId");
        UpdateToMysql update = new UpdateToMysql();
		/*
		 * update goodsId of one chip in the database
		 */
        String msg = "yes";
        int goodsId=0;
        int result = 0;
        if(strGoodsId!=null&&strGoodsId!=""){
        	goodsId = Integer.parseInt(strGoodsId);
        	result = update.UpdateGoodsIdOfOneChipByChipId(chipId, goodsId);
        }else{
        	msg="error";
        }
        if(result==0)
        	msg="error";
        
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
