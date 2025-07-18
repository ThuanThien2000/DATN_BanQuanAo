package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.mapper.ProductDetailMapper;
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
    public ProductDetail addByProductId(Long productId, ProductDetailDTO productDetailDTO) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return null;

        // Xử lý ảnh: nếu DTO có ảnh thì dùng, không thì tìm ảnh theo style
        String imgUrl = productDetailDTO.getImgUrl();
        if (imgUrl == null || imgUrl.isEmpty()) {
            ProductDetail existingDetail = productDetailRepository
                    .findFirstByProduct_IdAndStyleAndStatusAndImgUrlIsNotNullOrderByIdAsc(productId, productDetailDTO.getStyle(), 1);
            if (existingDetail != null) {
                imgUrl = existingDetail.getImgUrl();
            }
        }

        // Sinh mã productDetailCode
        String productDetailCode = generateProductDetailCode(product.getProductCode(), productDetailDTO.getStyle(), productDetailDTO.getSize());
        // Kiểm tra trùng mã
        if (productDetailRepository.existsByProductDetailCode(productDetailCode)) {
            throw new RuntimeException("Đã tồn tại ProductDetail style: " + productDetailDTO.getStyle() + ", size: " + productDetailDTO.getSize() + " cho sản phẩm: " + product.getProductName());
        }
        // Tạo mới ProductDetail
        ProductDetail detail = new ProductDetail();
        detail.setProduct(product);
        detail.setProductDetailCode(productDetailCode);
        detail.setStyle(productDetailDTO.getStyle());
        detail.setSize(productDetailDTO.getSize());
        if (productDetailDTO.getInventoryQuantity() == null || productDetailDTO.getInventoryQuantity() <= 0) {
            throw new RuntimeException("Tồn kho phải lớn hơn 0!");
        }
        detail.setInventoryQuantity(productDetailDTO.getInventoryQuantity());
        detail.setImgUrl(imgUrl);
        detail.setStatus(1); // còn hoạt động

        return productDetailRepository.save(detail);

    }

    // Hàm sinh mã productDetailCode từ productCode, style, size
    private String generateProductDetailCode(String productCode, String style, String size) {
        String stylePart = (style != null) ? style.replaceAll("\\s+", "").toUpperCase() : "";
        String sizePart = (size != null) ? size.replaceAll("\\s+", "").toUpperCase() : "";
        return productCode + stylePart + sizePart;
    }

    @Override
    public ProductDetail update(Long detailId, ProductDetailDTO detailDTO) {
        ProductDetail existing = productDetailRepository.findById(detailId).orElse(null);
        if (existing == null) {
            return null;
        }
        if (detailDTO.getInventoryQuantity() == null || detailDTO.getInventoryQuantity() <= 0) {
            throw new RuntimeException("Tồn kho phải lớn hơn 0!");
        }
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
        return ProductDetailMapper.toDTO(detail);
    }

    @Override
    public List<ProductDetailDTO> getActiveByProductId(Long productId) {
        List<ProductDetail> details = productDetailRepository.findActiveByProductId(productId);
        return details.stream().map(ProductDetailMapper::toDTO).toList();
    }
}