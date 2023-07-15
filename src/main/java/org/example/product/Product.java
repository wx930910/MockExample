package org.example.product;

import java.util.Set;

public abstract class Product implements Merchandise, Carriage {

    abstract String getProductName();

    protected abstract Set<Tag> getProductTags();

}
