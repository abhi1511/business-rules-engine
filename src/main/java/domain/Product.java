package domain;

import service.Category;

public class Product {
    private Category category;

    public Product(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
