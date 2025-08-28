package poly.quanlyquanao.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.mapper.ProductInfoMapper;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.Impl.HomeServiceImpl;

@Service
public class HomeService implements HomeServiceImpl {
    // Implement methods from HomeServiceImpl interface
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductInfo> getFeaturedProducts(Integer limit) {
        // Implementation logic here
        int safeLimit = (limit == null || limit < 0) ? 10 : limit; // default limit
        return productRepository.findFeaturedProducts().stream()
            .limit(safeLimit)
            .map(ProductInfoMapper::toProductInfo)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfo> getNewArrivals(Integer limit) {
        // Implementation logic here
        int safeLimit = (limit == null || limit < 0) ? 10 : limit; // default limit
        return productRepository.findAllActive().stream()
            .limit(safeLimit)
            .map(ProductInfoMapper::toProductInfo)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductInfo> getByUserType(String userType) {
        // Implementation logic here
        if (userType == null || userType.isEmpty()) {
            throw new IllegalArgumentException("User type cannot be null or empty");
        }
        return productRepository.findAllActive().stream()
            .filter(product -> product.getUserType().equals(userType))
            .map(ProductInfoMapper::toProductInfo)
            .collect(Collectors.toList());
    }
}
