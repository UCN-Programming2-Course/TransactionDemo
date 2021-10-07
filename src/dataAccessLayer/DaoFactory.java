package dataAccessLayer;

public class DaoFactory {

	public static OrderDao createOrderDao(DataContext dataContext) {
		
		
		return new OrderDaoImplementation(dataContext);
		
	}
}
