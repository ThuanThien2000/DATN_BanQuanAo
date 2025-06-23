package poly.quanlyquanao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.service.Impl.IProductDetailService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailService implements IProductDetailService {
    @Autowired
    ProductDetailRepository _productDetailRepository; // Đã thêm @Autowired cho repository

    @Override
    public ProductDetail getProductDetailById(Long id) {
        return _productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết sản phẩm với id: " + id));
    }

    @Override
    public List<ProductDetail> getAllProductDetail() {
        return _productDetailRepository.findAll();
    }

    @Override
    public ProductDetail addProductDetail(ProductDetail productDetail) {
        return _productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail updateProductDetail(Long id, ProductDetail productDetail) {
        Optional<ProductDetail> existingProductDetailOptional = _productDetailRepository.findById(id);
        if (existingProductDetailOptional.isPresent()) {
            ProductDetail existingProductDetail = existingProductDetailOptional.get();
            // Cập nhật các thuộc tính của chi tiết sản phẩm hiện có
            existingProductDetail.setProductDetailCode(productDetail.getProductDetailCode());
            existingProductDetail.setProduct(productDetail.getProduct()); // Cập nhật mối quan hệ Product
            existingProductDetail.setStyle(productDetail.getStyle());
            existingProductDetail.setSize(productDetail.getSize());
            existingProductDetail.setInventoryQuantity(productDetail.getInventoryQuantity());
            existingProductDetail.setImgUrl(productDetail.getImgUrl());
            existingProductDetail.setStatus(productDetail.getStatus());

            return _productDetailRepository.save(existingProductDetail);
        }
        return null; // Trả về null nếu không tìm thấy chi tiết sản phẩm để cập nhật
    }

    @Override
    public void deleteProductDetail(Long id) {
        if (!_productDetailRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy chi tiết sản phẩm với id: " + id);
        }
        _productDetailRepository.deleteById(id);
    }
}
