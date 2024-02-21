package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cart;
import model.Product;

public class ProductDAO {
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public int addProduct(Product product)
	{
		String sql = "insert into products(Product_name,Product_description,Product_cost,Product_img) values ('"+product.getName()+"','"+product.getDescription()+"','"+product.getCost()+"','"+product.getImg()+"')";
		int i = 0 ;
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate(); // Row number
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeStatement(ps);
			DBConnector.closeConnection(conn);
		}
		return i;
	}
	
	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted = false;
		String sql = "delete from Products where Product_id = ?;";
		try {
			conn = DBConnector.getConnection();
			ps = conn.prepareStatement(sql);
			System.out.println("Deleted Product id:"+ps);
			ps.setInt(1,id);
			
			rowDeleted = ps.executeUpdate()>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeStatement(ps);
			DBConnector.closeConnection(conn);
				}
		return rowDeleted;
	}
	
	public ArrayList<Product> getProducts() throws SQLException
	{
	String query = "select * from products";
	Connection con = DBConnector.getConnection();
	PreparedStatement statement = con.prepareStatement(query);
	ResultSet rs = statement.executeQuery();
	ArrayList<Product> productList = new ArrayList<Product>();
	while (rs.next())
	{
	Product product = new Product();
	product.setName(rs.getString("Product_name"));
	product.setDescription(rs.getString("Product_description"));
	product.setCost(rs.getString("Product_cost"));
	product.setImg(rs.getString("Product_img"));
	product.setId(rs.getInt("Product_id"));
	
	productList.add(product);
	}
	statement.close();
	DBConnector.closeConnection(con);
	return productList;
	}
	
	public Product getProductWithId(int id) throws SQLException
	{
	String query = "select * from products where Product_id='"+id+"'";
	Connection con = DBConnector.getConnection();
	PreparedStatement statement = con.prepareStatement(query);
	ResultSet rs = statement.executeQuery();
	Product product=null;
	if (rs.next())
	{
	product = new Product();
	product.setName(rs.getString("Product_name"));
	product.setDescription(rs.getString("Product_description"));
	product.setCost(rs.getString("Product_cost"));
	product.setImg(rs.getString("Product_img"));
	product.setId(rs.getInt("Product_id"));
	}
	statement.close();
	DBConnector.closeConnection(con);
	return product;
	}
}
