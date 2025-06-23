package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.model.Product;
import java.util.List;

public interface IProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product updateProduct(Long id,Product product);
    void deleteProduct(Long id);
}
