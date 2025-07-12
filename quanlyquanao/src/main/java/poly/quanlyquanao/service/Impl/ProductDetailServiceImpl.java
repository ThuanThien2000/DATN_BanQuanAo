package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.ProductDetailService;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDetail addByProductId(Long productId, ProductDetail productDetail) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return null;

        productDetail.setProduct(product);
        productDetail.setStatus(1); // còn hoạt động
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail update(Long detailId, ProductDetail detail) {
        ProductDetail existing = productDetailRepository.findById(detailId).orElse(null);

        if (existing == null) {
            return null; // Không tìm thấy hoặc không đúng sản phẩm
        }

        existing.setSize(detail.getSize());
        existing.setStyle(detail.getStyle());
        existing.setInventoryQuantity(detail.getInventoryQuantity());

        // Nếu muốn cập nhật giá sản phẩm chung
        if (detail.getProduct() != null && detail.getProduct().getPrice() != null) {
            Product product = existing.getProduct();
            product.setPrice(detail.getProduct().getPrice());
            productRepository.save(product);
        }

        return productDetailRepository.save(existing);
    }

    @Override
    public boolean softDelete(Long productId, Long detailId) {
        ProductDetail detail = productDetailRepository.findById(detailId).orElse(null);
        if (detail != null && detail.getProduct().getId().equals(productId)) {
            detail.setStatus(0); // đánh dấu đã xoá mềm
            productDetailRepository.save(detail);
            return true;
        }
        return false;
    }

    @Override
    public ProductDetail findById(Long id) {
        ProductDetail detail = productDetailRepository.findById(id).orElse(null);
        return (detail != null) ? detail : null;
    }

    @Override
    public List<ProductDetail> getByProductId(Long productId) {
        return productDetailRepository.findByProductId(productId);
    }

    @Override
    public List<ProductDetail> getActiveByProductId(Long productId) {
        return productDetailRepository.findActiveByProductId(productId);
    }
}