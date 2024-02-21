package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BuyerDAO;
import model.Buyer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read the inputs
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		//Admin Login code
		
		if(username.equals("admin")&& password.equals("admin"))
		{
			System.out.println("Admin Login Success");
			RequestDispatcher rd =request.getRequestDispatcher("admin_home.jsp");
			rd.forward(request, response);
		}
		
		//Buyer Login
		else {
			BuyerDAO dao = new BuyerDAO();
			try {
				Buyer buyer = dao.verifyBuyer(username,password);
				if(buyer == null) {
					System.out.println("Login failed");
					out.write("Login failed");
					
				}
				else
				{	
					HttpSession session = request.getSession();
					session.setAttribute("buyer", buyer);
					System.out.println("Login Successful");
					out.write("Login Successful");
					RequestDispatcher rd = request.getRequestDispatcher("buyer_home.jsp");
					rd.forward(request, response);
				}
			}
			catch(SQLException e) {
				//TODO Auto-Generated catch block
				e.printStackTrace();
			}
		}
	}

}
