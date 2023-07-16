package org.example.product;

import java.util.Set;

public abstract class Product {

    abstract String getProductName();

    protected abstract Set<Tag> getProductTags();

    abstract Product deepCopy();

    abstract int getPrice();

    abstract Product decorateProduct();

}
