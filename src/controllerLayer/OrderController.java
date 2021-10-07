package controllerLayer;

import java.sql.SQLException;

import dataAccessLayer.DaoFactory;
import dataAccessLayer.DataContext;
import dataAccessLayer.OrderDao;
import modelLayer.Order;

public class OrderController {

	private final OrderDao orderDao; 
	
	public OrderController(DataContext dataContext) {
		
		orderDao = DaoFactory.createOrderDao(dataContext);
	}
	
	public void SaveOrder(Order order) {
		
		try {
			orderDao.create(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
