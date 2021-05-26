package service;

import domain.Order;
import domain.Product;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static service.Category.BOOK;
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
        assert listOfActions.size() == 1;
        assert listOfActions.contains("generate a packing slip for shipping");

    }

    @Test
    public void testExecuteForMultipleProduct() {
        Product physicalProduct = new Product(PHYSICAL_PRODUCT);
        Product book = new Product(BOOK);
        Order order = new Order();
        order.addProducts(Arrays.asList(physicalProduct, book));
        List<String> listOfActions = testSubject.execute(order);
        assert listOfActions.size() == 2;
        assert listOfActions.contains("generate a packing slip for shipping");
        assert listOfActions.contains("create a duplicate packing slip for the royalty department");

    }

}
