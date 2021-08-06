package data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import beans.MediaRental;

public class RentalDAO implements AutoCloseable{
	
	public static void main(String[] args) {
		try (RentalDAO dao = new RentalDAO())
		{
			System.out.println("Connection successful!");
			MediaRental rental = new MediaRental("z", "zz", new BigDecimal("0"), "zzz", 3, "zy");
			dao.save(rental);
			System.out.println(dao.get());
		} 
		catch(Exception e)
		{
			System.out.println("Connection Failed");
			e.printStackTrace();
		}
	}
	
	private Connection connection;
	
	public RentalDAO() throws Exception
	{
		connect();
	}
	
	public void connect() throws Exception
	{
		String url = "jdbc:mysql://localhost:3306/mediarental";;
		String user = "root";
		String password = "sql21";
		
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		this.connection = DriverManager.getConnection(url, user, password);
	}
	
	@Override
	public void close() throws Exception
	{
		if(connection != null && !connection.isClosed())
		{
			this.connection.close();
		}
		
	}
	
	public boolean save(MediaRental rental) throws SQLException
	{
		String sql = "insert into rental(RENTAL_NAME, RENTAL_TYPE, RENTAL_PRICE, RENTAL_CONDITION, RENTAL_RATING, RENTAL_CATEGORY) values (?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, rental.getRentalName());
		statement.setString(2, rental.getRentalType());
		statement.setBigDecimal(3, rental.getPrice());
		statement.setString(4, rental.getCondition());
		statement.setInt(5, rental.getRating());
		statement.setString(6, rental.getCategory());
		int rows = statement.executeUpdate();
				
		return rows > 0 ? true : false;
	}

	public List<MediaRental> get() throws SQLException
	{
		String sql = "select RENTAL_ID, RENTAL_NAME, RENTAL_TYPE, RENTAL_PRICE, RENTAL_CONDITION, RENTAL_RATING, RENTAL_CATEGORY from rental ";
		List<MediaRental> results = new LinkedList<>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet set = statement.executeQuery();
		while(set.next())
		{
			MediaRental rental = new MediaRental(set.getString("RENTAL_NAME"), set.getString("RENTAL_TYPE"), set.getBigDecimal("RENTAL_PRICE"), set.getString("RENTAL_CONDITION"), set.getInt("RENTAL_RATING"), set.getString("RENTAL_CATEGORY"));
			results.add(rental);
		}
		
//		System.out.println(results);
		
		return results;
	}
	
	public boolean update(MediaRental rental) throws SQLException
	{
		return false;
	}
	
	public boolean delete() throws SQLException
	{
		String sql = "delete from rental";
		PreparedStatement statement = connection.prepareStatement(sql);
		int rows = statement.executeUpdate();
		return rows > 0 ? true : false;
	}
}
