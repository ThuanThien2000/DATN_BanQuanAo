package poly.quanlyquanao.service;

import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.ProductDetail;

import java.util.List;

public interface ProductDetailService {
    ProductDetail addByProductId(Long productId, ProductDetailDTO productDetailDTO);
    ProductDetail update(Long detailId, ProductDetailDTO productDetailDTO);
    boolean softDelete(Long productId, Long detailId);
    ProductDetailDTO findById(Long id);
    List<ProductDetailDTO> getActiveByProductId(Long productId);
}