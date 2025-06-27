package poly.quanlyquanao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.Impl.IProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository _productRepository;

    @Override
    public Product getProductById(Long id) {
        return _productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với id: "+ id));
    }

    @Override
    public List<Product> getAllProducts() {
        return _productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return _productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProductOptional = _productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            // Cập nhật các thuộc tính của sản phẩm hiện có
            existingProduct.setProductCode(product.getProductCode());
            existingProduct.setProductName(product.getProductName());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setUserType(product.getUserType());
            existingProduct.setMaterial(product.getMaterial());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImgUrlOne(product.getImgUrlOne());
            existingProduct.setImgUrlTwo(product.getImgUrlTwo());
            existingProduct.setImgUrlThree(product.getImgUrlThree());
            existingProduct.setStatus(product.getStatus());

            return _productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!_productRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy sản phẩm với id: " + id);
        }
        _productRepository.deleteById(id);
    }
}
