package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.ProductDetail;

public class ProductDetailMapper {
    public static ProductDetailDTO toDTO(ProductDetail detail) {
        if (detail == null) return null;
        return new ProductDetailDTO(
            detail.getId(),
            detail.getProductDetailCode(),
            detail.getProduct() != null ? detail.getProduct().getId() : null,
            detail.getStyle(),
            detail.getSize(),
            detail.getInventoryQuantity(),
            detail.getImgUrl(),
            detail.getStatus()
        );
    }
}
