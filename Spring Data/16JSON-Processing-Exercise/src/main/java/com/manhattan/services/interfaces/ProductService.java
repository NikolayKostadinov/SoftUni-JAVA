package com.manhattan.services.interfaces;

import com.manhattan.models.productsShop.dtos.ProductsInRangeDto;
import com.manhattan.models.productsShop.entities.Product;

import java.math.BigDecimal;

public interface ProductService {
    void saveAll(Iterable<Product> collect);
    Iterable<ProductsInRangeDto> getNotBoughtProductsWithPriceInRange(BigDecimal min, BigDecimal max);

    boolean hasNoRecords();
}
