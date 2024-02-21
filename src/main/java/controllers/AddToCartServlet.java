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

import database.ProductDAO;
import model.Cart;
import model.Product;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductWithId(id);

            if (product != null) {
                HttpSession session = request.getSession();
                ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");

                if (cartList == null) {
                    cartList = new ArrayList<>();
                    session.setAttribute("cart-list", cartList);
                }

                boolean itemExists = false;

                for (Cart cart : cartList) {
                    if (cart.getId() == id) {
                        itemExists = true;
                        break;
                    }
                }

                if (itemExists) {
                    out.println("The item is already in the cart");
                } else {
                    Cart cart = new Cart();
                    cart.setId(id);
                    cart.setName(product.getName());
                    cart.setDescription(product.getDescription());
                    cart.setCost(product.getCost());
                    cart.setQuantity(1);

                    cartList.add(cart);
                    out.println("The item added to the cart");
                }

                response.sendRedirect("mycart.jsp");
            } else {
                out.println("Invalid product ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("An error occurred while processing the request");
        }
    }
}
