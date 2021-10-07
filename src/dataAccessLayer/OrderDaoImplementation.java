package dataAccessLayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelLayer.Order;

class OrderDaoImplementation implements OrderDao {

	private final DataContext dataContext;

	public OrderDaoImplementation(DataContext dataContext) {

		this.dataContext = dataContext;
	}

	@Override
	public int create(Order order) throws SQLException {

		Connection connection = dataContext.getConnection();
		connection.setAutoCommit(false);

		try {

			// first insert order
			String insertOrderSQL = "INSERT INTO Orders VALUES (?)";
			PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderSQL,
					Statement.RETURN_GENERATED_KEYS);
			insertOrderStatement.setDate(1, new Date(System.currentTimeMillis()));
			int affectedRows = insertOrderStatement.executeUpdate();

			if (affectedRows == 0) {
				// nothing was inserted
				throw new SQLException("Creating order failed, no rows affected.");
			}

			// then get the new order id
			ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				long orderId = generatedKeys.getLong(1);

				// last insert the order lines
				for (int idx = 0; idx < order.getOrderLines().size(); idx++) {

					String insertOrderlineSQL = "INSERT INTO Orderlines VALUES (?, ?, ?)";
					PreparedStatement insertOrderlineStatement = connection.prepareStatement(insertOrderlineSQL);
					insertOrderlineStatement.setLong(1, orderId);
					insertOrderlineStatement.setString(2, order.getOrderLines().get(idx).getItem());
					insertOrderlineStatement.setInt(3, order.getOrderLines().get(idx).getQuantity());
					if (insertOrderlineStatement.executeUpdate() == 0) {
						throw new SQLException("Creating order failed, could not insert orderline.");
					}
				}

			} else {
				throw new SQLException("Creating order failed, no ID obtained.");
			}

			connection.commit();
			return affectedRows;


		} catch (SQLException e) {
		
			connection.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			connection.setAutoCommit(true);
		}

		return 0;
	}

}
