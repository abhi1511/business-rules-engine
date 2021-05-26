package service;

import businessrules.Rule;
import domain.Order;
import domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRulesEngine {

    private Map<Category, List<Rule>> productToBusinessRulesMapping = new HashMap<>();


    public List<String> execute(Order order) {
        List<Product> listOfProducts = order.getProductList();
        List<Rule> listOfRule = new ArrayList<>();
        List<String> listOfActions;

        listOfProducts.stream()
                .filter(product -> productToBusinessRulesMapping.get(product.getCategory()) != null)
                .forEach(product -> {
                    listOfRule.addAll(productToBusinessRulesMapping.get(product.getCategory()));
                });

        listOfActions = listOfRule.stream().map(Rule::apply).collect(Collectors.toList());
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
