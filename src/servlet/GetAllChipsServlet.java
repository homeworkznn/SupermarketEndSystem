package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.GetDataFromMySql;
import model.Authority;
import model.Chip;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetAllChipsServlet
 */
@WebServlet("/GetAllChipsServlet")
public class GetAllChipsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllChipsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "text/html;charset=UTF-8");    
        response.setContentType("text/html;charset=utf-8"); 
        
		/*
		 * select all chips in the database
		 */
        GetDataFromMySql getData =new GetDataFromMySql();
        String msg = "yes";
        List<Chip> list = new ArrayList<>();
        list = getData.getAllChips();
        /*Chip chip = new Chip();
        chip.setId("A456");
        chip.setState(1);
        chip.setGoodsId(12);
        list.add(chip);*/
        
        //生成JSON数据      
        JSONObject object = new JSONObject();  
        object.element("msg", msg);
        try {      
        	int i=0;
        	JSONObject j0  = new JSONObject();
             for(Chip a : list) {   
            	 JSONObject j1  = new JSONObject();
            	 j1.put("id", a.id);
            	 j1.put("goodsId", a.goodsId);
            	 j1.put("state", a.state);
            	 j0.put("chip"+i, j1);
            	 i++;
             }  
             object.element("res", j0);  
         } catch (Exception e) {    
             e.printStackTrace();    
         }    
        
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
