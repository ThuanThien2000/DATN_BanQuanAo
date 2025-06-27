package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.model.ProductDetail;
import java.util.List;

public interface IProductDetailService {
    ProductDetail getProductDetailById(Long id);
    List<ProductDetail> getAllProductDetail();
    ProductDetail addProductDetail(ProductDetail productDetail);
    ProductDetail updateProductDetail(Long id, ProductDetail productDetail);
    void deleteProductDetail(Long id);
}
