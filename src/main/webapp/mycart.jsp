<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Cart"%>
<%@page import="database.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart Page</title>
	<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body >
	<!-- Grey with black text -->
<nav class="navbar navbar-expand-sm " style="background-color:  #092961">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">

    <a class="navbar-brand">
      <img src="./asserts/logo.png" alt="Logo" style="width:50px;" class="rounded-pill"> 
    </a>
        	    <a class="navbar-brand" style="color:#f59542;">ELECTRONICS STORE</a>

      
      <li class="nav-item">
        <a class="nav-link text-light" href="buyer_home.jsp">View Products</a>
      </li>
   
     <li class="nav-item">
        <a class="nav-link text-light" href="profile.jsp">My Profile</a>
      </li>   
      
      <li class="nav-item">
        <a class="nav-link text-light" href="mycart.jsp">My cart</a>
      </li>  
      
      <li class="nav-item">
        <a class="nav-link text-light" href="myorders.jsp">My Orders</a>
      </li>  
    </ul>

    <ul class="navbar-nav ms-auto ">

      <li class="nav-item">
        <a class="nav-link text-light " href="index.html">Logout</a>
      </li>
      
    </ul>
    
  </div>
</nav>
</head>
<body>
   
  	 <div class="container w-50 p-5 my-5 text-dark">
    <h1 style="text-align: center;">Product List</h1>

    <table class="table table-danger table-striped table-bordered border-white border-5">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
            if (cartList != null && !cartList.isEmpty()) {
                for (Cart cart : cartList) {
        %>
        <tr>
            <td>
                <input type="text" name="id" value="<%= cart.getId() %>" readonly/>
            </td>
            <td><%= cart.getName() %></td>
            <td><%= cart.getDescription() %></td>
            <td><%= cart.getCost() %></td>
            <td><a href='DeleteCartProductServlet?id=<%= cart.getId() %>'>Delete</a></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">The cart is empty</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>