package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;

@WebServlet("/DeleteCartProductServlet")
public class DeleteCartProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String idParam = request.getParameter("id");

            if (idParam == null || !idParam.matches("\\d+")) {
                out.write("Invalid cart item ID");
                return;
            }

            int id = Integer.parseInt(idParam);
            HttpSession session = request.getSession();
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

            if (cartList == null || cartList.isEmpty()) {
                out.write("The cart is empty");
            } else if (id < 0 || id >= cartList.size()) {
                out.write("Invalid cart item ID");
            } else {
                cartList.remove(id);
                out.write("The cart item has been deleted");
                // Redirect or refresh the page after deletion
                response.sendRedirect("mycart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("An error occurred");
        }
    }
}
