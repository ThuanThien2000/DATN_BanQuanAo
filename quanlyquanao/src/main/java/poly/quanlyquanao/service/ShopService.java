package poly.quanlyquanao.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.mapper.ProductInfoMapper;
import poly.quanlyquanao.repository.ProductRepository;

@Service
public class ShopService {
    
    @Autowired
    private ProductRepository productRepository;
    public List<ProductInfo> getAllProductInfo() {
        return productRepository.findAllActive().stream()
                .map(ProductInfoMapper::toProductInfo)
                .filter(pi -> pi != null)
                .toList();
    }

    // Lấy sản phẩm theo loại người dùng
    public List<ProductInfo> getProductsByUserType(String userType) {
        String lowerUserType = userType.toLowerCase();
        return productRepository.findAllActive().stream()
                .filter(product -> product.getUserType().toLowerCase().equals(lowerUserType))
                .map(ProductInfoMapper::toProductInfo)
                .filter(pi -> pi != null)
                .toList();
    }

    // lấy sản phẩm theo chuỗi tìm kiếm
    public List<ProductInfo> searchProducts(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
            return productRepository.findAllActive().stream()
            .filter(product -> product.getProductName() != null 
                    && product.getProductName().toLowerCase().contains(lowerKeyword))
            .map(ProductInfoMapper::toProductInfo)
            .filter(Objects::nonNull)
            .toList();
    }

}
