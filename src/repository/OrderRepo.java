package repository;

import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepo {
    public List<Order> orderHistory(int customerId);
    public boolean addOrder(Order order);


}
//### Ordrar  (1days)
//- Se orderhistorik för en kund (SELECT med JOIN på orders och customers WHERE customer_id = ?)
//- Lägga en order (INSERT into orders)
//- Lägga en order med flera produkter (INSERT into orders_products)
//- Ange antal av produkten vid orderläggning (Görs tillsammans med ovan INSERT)
//- Se totala priset på en order (Räkna ihop priset gånger antalet)

