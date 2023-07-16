package org.example.product;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TestProductService {

    @Mock
    Product product;


    @Test
    public void testWrapProduct() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ProductService productService = new ProductService();
        productService.wrapProduct(product, "org.example.product.MockWrapDelegate");
    }

}
