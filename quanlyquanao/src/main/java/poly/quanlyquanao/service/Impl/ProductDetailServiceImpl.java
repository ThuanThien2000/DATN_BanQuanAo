package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.repository.ProductDetailRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.ProductDetailService;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    private ProductDetailDTO toDTO(ProductDetail detail) {
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

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDetail addByProductId(Long productId, ProductDetailDTO productDetailDTO) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return null;
        ProductDetail detail = new ProductDetail();
        detail.setProduct(product);
        detail.setProductDetailCode(productDetailDTO.getProductDetailCode());
        detail.setStyle(productDetailDTO.getStyle());
        detail.setSize(productDetailDTO.getSize());
        detail.setInventoryQuantity(productDetailDTO.getInventoryQuantity());
        detail.setImgUrl(productDetailDTO.getImgUrl());
        detail.setStatus(1); // còn hoạt động
        ProductDetail saved = productDetailRepository.save(detail);
        return saved;
    }

    @Override
    public ProductDetail update(Long detailId, ProductDetailDTO detailDTO) {
        ProductDetail existing = productDetailRepository.findById(detailId).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setSize(detailDTO.getSize());
        existing.setStyle(detailDTO.getStyle());
        existing.setInventoryQuantity(detailDTO.getInventoryQuantity());
        existing.setImgUrl(detailDTO.getImgUrl());

        ProductDetail saved = productDetailRepository.save(existing);
        return saved;
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
    public ProductDetailDTO findById(Long id) {
        ProductDetail detail = productDetailRepository.findById(id).orElse(null);
        return toDTO(detail);
    }

    @Override
    public List<ProductDetailDTO> getActiveByProductId(Long productId) {
        List<ProductDetail> details = productDetailRepository.findActiveByProductId(productId);
        return details.stream().map(this::toDTO).toList();
    }
}