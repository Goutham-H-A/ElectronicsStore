<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="model.Product"%>
<%@page import="database.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
    

<!DOCTYPE html>
<html>
<head>
	<title>Products Page</title>
	
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


	<%
	ProductDAO dao = new ProductDAO();
	ArrayList<Product> plist = (ArrayList<Product>) dao.getProducts();
	%>
	<%
	if (plist.size() != 0) {
		for (Product product : plist) {
	%>
		<div style="display:inline-block;padding-left:20px; width:300px">
			<img src="productimg/<%=product.getImg() %>" class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title" name="name"><%=product.getName() %></h5>
				<p class="card-text" ><%=product.getDescription() %></p>
				<p class="card-text" ><%=product.getCost() %></p>
				<a href="AddToCartServlet?id=<%=product.getId()%>"class="btn btn-primary">Add to Cart</a>
				<a href="#" class="btn btn-primary" style="margin-left:50px;text-align:center;">Buy Now</a>
			</div>
</div>
	<%
	}
	%>




	<%
	} else {
	%><p style="text-align: center;">Admin has not added any products.</p>
	<%
	}
	%>

</body>
</html>