package service;

import domain.Order;
import domain.Product;
import org.junit.Test;

import java.util.List;

import static service.Category.PHYSICAL_PRODUCT;

public class OrderRulesEngineTest {

    private OrderRulesEngine testSubject = new OrderRulesEngine();

    @Test
    public void testExecuteForEmptyOrder() {
        Product physicalProduct = new Product(PHYSICAL_PRODUCT);
        Order order = new Order();
        order.addProduct(physicalProduct);
        List<String> listOfActions = testSubject.execute(order);
        assert listOfActions.size() == 0;

    }

    @Test
    public void testExecuteForSingleProduct() {
        Product physicalProduct = new Product(PHYSICAL_PRODUCT);
        Order order = new Order();
        order.addProduct(physicalProduct);
        List<String> listOfActions = testSubject.execute(order);
        assert listOfActions.contains("generate a packing slip for shipping");

    }
}
