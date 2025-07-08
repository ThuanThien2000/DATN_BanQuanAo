package poly.quanlyquanao.service;

import poly.quanlyquanao.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    ProductDetail addByProductId(Long productId, ProductDetail productDetail);
    ProductDetail update(Long productId, Long detailId, ProductDetail productDetail);
    boolean softDelete(Long productId, Long detailId);
    ProductDetail findById(Long productId, Long detailId);
    List<ProductDetail> getByProductId(Long productId);
    List<ProductDetail> getActiveByProductId(Long productId);
}