package service;

import businessrules.Rule;
import domain.Order;
import domain.Product;

import java.util.*;

public class OrderRulesEngine {

    private Map<Category, List<Rule>> productToBusinessRulesMapping = new HashMap<>();


    public List<String> execute(Order order) {
        List<Product> listOfProducts = order.getProductList();
        List<String> listOfActions = new ArrayList<>();

        listOfProducts.stream()
                .filter(product -> productToBusinessRulesMapping.get(product.getCategory()) != null)
                .map()
        return listOfActions;
    }

    public void addRules(Category category, Rule rule) {
        if (productToBusinessRulesMapping.get(category) == null) {
            List<Rule> rules = new ArrayList<>();
            rules.add(rule);
            productToBusinessRulesMapping.put(category, rules);
        } else {
            productToBusinessRulesMapping.get(category).add(rule);
        }
    }
}
