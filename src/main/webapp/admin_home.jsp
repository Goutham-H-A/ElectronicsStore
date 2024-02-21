<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="model.Buyer"%>
<%@page import="database.BuyerDAO"%>
<%@page import="java.util.ArrayList"%>
    

<!DOCTYPE html>
<html>
<head>
	<title></title>
	  <style type="text/css">
    body
    {
      background : url('background.jpeg');
      background-size: cover;
    }
  </style>
	
	<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body >
	<!-- Grey with black text -->
<nav class="navbar navbar-expand-sm py-3 border border-light" style="background-color: #092961">
  <div class="container-fluid fw-bolder fs-5">
    <ul class="navbar-nav">

<li>
        <a class="navbar-brand" href="#">
      <img src="./asserts/logo.png" alt="Logo" style="width:40px;" class="rounded-pill"> 
    </a>
        	    <a class="navbar-brand" href="#"style="color:#f59542;">ELECTRONICS STORE</a>
        	    </li>

	 <li class="nav-item ">
        <a class="nav-link active text-light" href="admin_home.jsp">View Buyers</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="product.html">Add Product</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="view_products.jsp">View Products</a>
      </li>
      
    </ul>

    
    <ul class="navbar-nav ms-auto ">
      <li class="nav-item">
        <a class="nav-link text-light" href="index.html">Log Out</a>
      </li>
    </ul>
    
  </div>
</nav>
	<form method="post" action="DeleteBuyerServlet">
  		<div class="container w-50 p-5 my-5 text-dark">
		<h1 style="text-align: center;">Buyer List</h1>

		<%
		BuyerDAO dao = new BuyerDAO();
			   	ArrayList<Buyer> blist = (ArrayList<Buyer>)dao.getBuyers();
				if(blist.size()!=0){
				
		%>
		<table class="table table-danger table-striped table-bordered border-white border-5">
	
			<thead>
				<tr>
				<th>Sl.No</th>
				<th>Name</th>
				<th>Mail</th>
				<th>Mobile</th>
				<th colspan="2">Action</th>
				</tr>
			</thead>
			<tbody>
			<%for (Buyer buyer:blist){
	  			%>
    			<tr>
    			<td>
    				<input type="text" name="id" value="<%= buyer.getId() %>" readonly/>
    			</td>
   	  <td><%=buyer.getName()%></td>
   	  <td><%=buyer.getMail()%></td>
   	  <td><%=buyer.getMobile()%></td>
   	  <td><a href='DeleteBuyerServlet?id=<%=buyer.getId()%>'>Delete</a></td>
   	  </tr> 
		<%}%>
			
			</tbody>
		</table>
		<%
		}else{ %><p style="text-align: center;">No registered Buyers.</p><%} %>
    </div>
    </form>
</body>
</html>