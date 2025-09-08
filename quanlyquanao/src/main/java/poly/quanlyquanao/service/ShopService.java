package poly.quanlyquanao.service;

import java.util.List;

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


}
