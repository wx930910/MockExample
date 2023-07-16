package org.example.product;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TestProductService {

    @Mock
    Product product;
    @Mock
    Tag tag;

    private final static String PRODUCT_NAME = "product";
    private final static int PRODUCT_PRICE = 10;
    private final static Double PRODUCT_UNIT_DELIVERY_PRICE = 1.0;


    @BeforeEach
    public void setup() {
        initMocks(this);
    }
    @Test
    public void testWrapProduct() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ProductService productService = new ProductService();
        productService.wrapProduct(product, "org.example.product.MockWrapDelegate");
        verify(product).decorateProduct();
    }

    @Test
    public void testDecorateProduct() {
        ProductService productService = new ProductService();
        MockProduct mockProduct = new MockProduct((p) -> {return ((MockProduct) p.deepCopy()).addPrice(10);}, PRODUCT_PRICE);
        assertEquals(30, mockProduct.decorateProduct().decorateProduct().getPrice());
    }

    class MockProduct extends Product implements Carriage {

        ProductDecoratorSupplier productDecoratorSupplier;
        int price;
        public MockProduct(ProductDecoratorSupplier productDecoratorSupplier, int price) {
            this.productDecoratorSupplier = productDecoratorSupplier;
            this.price = price;
        }

        @Override
        public Double getUnitDeliveryPrice() {
            return PRODUCT_UNIT_DELIVERY_PRICE;
        }

        @Override
        String getProductName() {
            return PRODUCT_NAME;
        }

        @Override
        protected Set<Tag> getProductTags() {
            return Set.of(tag);
        }

        @Override
        Product deepCopy() {
            return new MockProduct(productDecoratorSupplier, price);
        }

        @Override
        int getPrice() {
            return price;
        }

        @Override
        Product decorateProduct() {
            return productDecoratorSupplier.decorate(this);
        }

        public Product addPrice(int price) {
            this.price += price;
            return this;
        }

        interface ProductDecoratorSupplier {
            Product decorate(MockProduct product);
        }

    }

}
