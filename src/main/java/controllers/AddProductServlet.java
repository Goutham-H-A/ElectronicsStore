// ... (your existing imports)

@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProductServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Read the inputs
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String cost = request.getParameter("cost");
            Part file = request.getPart("image");

            String imageFileName = file.getSubmittedFileName();

            String uploadPath = "C:/Users/ADMIN/Desktop/ElectronicsStore/src/main/webapp/productimg/" + imageFileName;

            try (FileOutputStream fos = new FileOutputStream(uploadPath);
                    InputStream is = file.getInputStream()) {

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception gracefully, e.g., return an error message or redirect to an error page
                response.sendRedirect("error.jsp");
                return;
            }

            // Create an object of the Model Class-Product
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setCost(cost);
            product.setImg(imageFileName);

            ProductDAO dao = new ProductDAO();
            int success = dao.addProduct(product);

            if (success > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("product", product);
                System.out.println("Product added successfully");
                response.sendRedirect("view_products.jsp");
            } else {
                System.out.println("Product addition failed");
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception gracefully, e.g., return an error message or redirect to an error page
            response.sendRedirect("error.jsp");
        }
    }
}
