package poly.quanlyquanao.service;

import poly.quanlyquanao.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    ProductDetail addByProductId(Long productId, ProductDetail productDetail);
    ProductDetail update(Long id, ProductDetail productDetail);
    boolean softDelete(Long id);
    ProductDetail findById(Long id);
    List<ProductDetail> getByProductId(Long productId);
    List<ProductDetail> getActiveByProductId(Long productId);
}
