package poly.quanlyquanao.service;

import poly.quanlyquanao.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean softDeleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllActiveProducts();
    List<Product> getLowStockProducts(int threshold);
}