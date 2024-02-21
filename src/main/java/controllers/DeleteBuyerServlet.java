package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.BuyerDAO;


/**
 * Servlet implementation class DeleteBuyerServlet
 */
@WebServlet("/DeleteBuyerServlet")
public class DeleteBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBuyerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        try {
	        BuyerDAO dao = new BuyerDAO();
			boolean success = dao.deleteBuyer(id);
			if(success) {
	        response.sendRedirect("admin_home.jsp");
	        }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
}
}
		
	}

