package org.example.product;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    // Override JDK Hashcode()
    public List<Product> sortProducts(List<Product> products) {
        Collections.sort(products, Comparator.comparingInt(Object::hashCode));
        return products;
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
