package poly.quanlyquanao.service;

import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.dto.ProductDTO;

import java.util.List;
import poly.quanlyquanao.dto.ProductDTO;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProduct(Long id, ProductDTO productDTO);
    boolean softDeleteProduct(Long id);
    ProductDTO getProductById(Long id);
    List<Product> getAllActiveProducts();
    List<ProductDTO> getAllActiveProductDTOs();
    List<Product> getLowStockProducts(int threshold);
}