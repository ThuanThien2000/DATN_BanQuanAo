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
        productDetail.setStatus(1);
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail update(Long id, ProductDetail detail) {
        ProductDetail existing = productDetailRepository.findById(id).orElse(null);
        
        if (existing != null) {
            existing.setSize(detail.getSize());
            existing.setStyle(detail.getStyle());
            existing.setInventoryQuantity(detail.getInventoryQuantity());

            // ✅ Gọi price từ Product gắn với ProductDetail
            if (detail.getProduct() != null && detail.getProduct().getPrice() != null) {
                Product product = existing.getProduct();
                product.setPrice(detail.getProduct().getPrice());
                productRepository.save(product); // nhớ inject productRepository
            }

            return productDetailRepository.save(existing);
        }

        return null;
    }

    @Override
    public boolean softDelete(Long id) {
        ProductDetail detail = productDetailRepository.findById(id).orElse(null);
        if (detail != null) {
            detail.setStatus(0);
            productDetailRepository.save(detail);
            return true;
        }
        return false;
    }

    @Override
    public ProductDetail findById(Long id) {
        return productDetailRepository.findById(id).orElse(null);
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
