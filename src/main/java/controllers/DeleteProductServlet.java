package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ProductDAO;
@MultipartConfig
/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		        int id = Integer.parseInt(request.getParameter("id"));
		        try {
		        ProductDAO dao = new ProductDAO();
				boolean success = dao.deleteProduct(id);
				if(success) {
					System.out.println("Product deleted is successful");
		            response.sendRedirect("view_products.jsp");
		        }
		        }
		        catch (SQLException e) {
		            e.printStackTrace();
}
		        }
}
