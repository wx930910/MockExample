package org.example.product;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    // Override JDK Hashcode()
    public List<Product> sortProducts(List<Product> products) {
        Collections.sort(new ArrayList<>(products), Comparator.comparingInt(Object::hashCode));
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

    // Use class metadata
    public void wrapProduct(Product product, String wrapDelegate) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = ProductWrapDelegate.class.getClassLoader();
        Class<ProductWrapDelegate> delegateClass = (Class<ProductWrapDelegate>) classLoader.loadClass(wrapDelegate);
        ProductWrapDelegate delegate = delegateClass.newInstance();
        delegate.wrapProduct(product);
    }

    // Self reference
    public List<Product> copyProducts(List<Product> products, Tag productTag) {
        final List<Product> copied = new ArrayList<>();
        filterProducts(products, productTag).stream().forEach(p -> copied.add(p.deepCopy()));
        return copied;
    }

}
