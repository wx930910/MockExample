package org.example.product;

public class MockWrapDelegate  extends ProductWrapDelegate {

    @Override
    public void wrapProduct(Product product) {
        product.decorateProduct();
    }

}
