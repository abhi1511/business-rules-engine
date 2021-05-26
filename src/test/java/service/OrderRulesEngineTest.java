package service;

import businessrules.*;
import domain.Order;
import domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static service.Category.*;

public class OrderRulesEngineTest {

    private OrderRulesEngine testSubject = new OrderRulesEngine();

    @Before
    public void setup() {
        testSubject.addRules(PHYSICAL_PRODUCT, new GenerateSlip());
        testSubject.addRules(PHYSICAL_PRODUCT, new GenerateCommissionPayment("physical product"));
        testSubject.addRules(BOOK, new GenerateCommissionPayment("book"));
        testSubject.addRules(BOOK, new DuplicateSlip());
        testSubject.addRules(UPGRADE_MEMBERSHIP, new UpgradeMembership());
        testSubject.addRules(UPGRADE_MEMBERSHIP, new EmailNotification("Upgrade"));
        testSubject.addRules(ACTIVATE_MEMBERSHIP, new ActivateMembership());
        testSubject.addRules(ACTIVATE_MEMBERSHIP, new EmailNotification("activation"));
        testSubject.addRules(VIDEO_FOR_LEARNING_SKI, new AddFreeVideo());
    }

    @Test
    public void testExecuteForEmptyOrder() {
        Order order = new Order();
        List<String> listOfActions = testSubject.execute(order);
        assertEquals(0, listOfActions.size());

    }

    @Test
    public void testExecuteForSingleProduct() {
        Product physicalProduct = new Product(PHYSICAL_PRODUCT);
        Order order = new Order();
        order.addProduct(physicalProduct);
        List<String> listOfActions = testSubject.execute(order);
        assertEquals(2, listOfActions.size());
        assertTrue(listOfActions.contains("generate a packing slip for shipping"));
        assertTrue(listOfActions.contains("generate a commission payment to the agent for physical product"));

    }

    @Test
    public void testExecuteForMultipleProduct() {
        Product physicalProduct = new Product(PHYSICAL_PRODUCT);
        Product book = new Product(BOOK);
        Product activateMembership = new Product(ACTIVATE_MEMBERSHIP);
        Order order = new Order();
        order.addProducts(Arrays.asList(physicalProduct, book, activateMembership));
        List<String> listOfActions = testSubject.execute(order);
        assertEquals(6, listOfActions.size());
        assertTrue(listOfActions.contains("generate a packing slip for shipping"));
        assertTrue(listOfActions.contains("create a duplicate packing slip for the royalty department"));
        assertTrue(listOfActions.contains("generate a commission payment to the agent for book"));
        assertTrue(listOfActions.contains("generate a commission payment to the agent for physical product"));
        assertTrue(listOfActions.contains("activate that membership"));
        assertTrue(listOfActions.contains("e-mail the owner and inform them of the activation"));

    }

}
