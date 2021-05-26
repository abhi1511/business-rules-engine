package domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void addProducts(List<Product> productList) {
        this.productList.addAll(productList);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
