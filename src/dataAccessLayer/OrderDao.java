package dataAccessLayer;

import java.sql.SQLException;

import modelLayer.Order;

public interface OrderDao {
	
	int create(Order order) throws SQLException;

}
