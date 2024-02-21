package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cart;
import model.Product;
public class CartDAO {

	private Connection conn = null;
	private PreparedStatement ps = null;
	
	public int addcart(Cart cart)
	{
		String sql = "insert into cart(Pr_name,Pr_desc,Pr_cost,Pr_quan) values ('"+cart.getName()+"','"+cart.getDescription()+"','"+cart.getCost()+"','"+cart.getQuantity()+"')";
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
	

}
