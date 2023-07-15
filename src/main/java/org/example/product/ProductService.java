package org.example.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    // Override JDK Equals()
    public int compareProductName(Product product1, Product product2) throws Exception {
        if (product1.equals(product2)) {
            throw new Exception(String.format("Cannot compare name for the same product [%s]", product1));
        }
        return product1.getProductName().compareTo(product2.getProductName());
    }

    // Override protected method
    public List<Product> filterProducts(List<Product> products, Tag productTag) {
        return products.stream().filter(p -> p.getProductTags().contains(productTag)).collect(Collectors.toList());
    }

    // Inherit multiple classes
    public Double getProductPrice(Product product, Double deliveryDistance) {
        return product.getPrice() + product.getUnitDeliveryPrice() * deliveryDistance;
    }

}
