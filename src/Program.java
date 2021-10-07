import java.util.ArrayList;
import java.util.List;

import controllerLayer.OrderController;
import modelLayer.Order;
import modelLayer.OrderLine;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OrderController ctrl = new OrderController(new Database());
		
		List<OrderLine> orderLines = new ArrayList<OrderLine>(); 
		
		OrderLine ol1 = new OrderLine();
		ol1.setItem("Ristede L�g");
		ol1.setQuantity(2);
		orderLines.add(ol1);
		
		OrderLine ol2 = new OrderLine();
		ol2.setItem("Ristede p�lser");
		ol2.setQuantity(2);
		orderLines.add(ol2);
		
		OrderLine ol3 = new OrderLine();
		ol3.setItem("Br�d");
		ol3.setQuantity(2);
		orderLines.add(ol3);		
		
		Order order = new Order();
		order.setOrderLines(orderLines);
		
		ctrl.SaveOrder(order);
	}

}
