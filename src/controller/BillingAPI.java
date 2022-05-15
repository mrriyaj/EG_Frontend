package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servermodel.Billing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

 //========================================================IMPLEMENTS DC BUS===================================================================================
/**
 * Servlet implementation class CartAPI
 */
@WebServlet("/BillingAPI")
public class BillingAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Billing bi = new Billing();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillingAPI() {
        super();
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	//=================================================for INSERT OPERATION====================================================================
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = bi.insertBilling(request.getParameter("billing_id"),
				request.getParameter("mc_id"),
				request.getParameter("user_id"),
				request.getParameter("usege"),
				request.getParameter("total"),
				request.getParameter("status"));
		response.getWriter().write(output);
	}

	
	// ========================================CONVERT REQUEST PARAMETERS TO MAP===============================================================================
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params)
					{
						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
		}
		catch (Exception e)
		{
		}
		return map;
	}

	//======================================================for UPDATE OPEARTION================================================================================
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = bi.updateBilling(paras.get("hidBillIDSave").toString(),
				paras.get("billing_id").toString(),
				paras.get("mc_id").toString(),
				paras.get("user_id").toString(),
				paras.get("usege").toString(),
				paras.get("total").toString(),
				paras.get("status").toString());
		response.getWriter().write(output);
	}

	//===========================================================for DELETE OPERATION================================================================================
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		String output = bi.deleteBilling(paras.get("billID").toString());
		response.getWriter().write(output);
	}
	

}
