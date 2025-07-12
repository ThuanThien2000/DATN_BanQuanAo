
package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.dto.ImageDTO;
import poly.quanlyquanao.dto.ProductDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.model.Image;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.repository.CategoryRepository;
import poly.quanlyquanao.repository.ImageRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.ProductService;

import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> getAllActiveProductDTOs() {
        List<Product> products = getAllActiveProducts();
        return products.stream().map(this::toProductDTO).toList();
    }

    private ProductDTO toProductDTO(Product product) {
        Set<ImageDTO> imageDTOs = null;
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            imageDTOs = product.getImages().stream()
                .filter(img -> img.getStatus() == 1)
                .map(img -> new ImageDTO(img.getId(), img.getImageUrl(), img.getStatus()))
                .collect(java.util.stream.Collectors.toSet());
        }
        return new ProductDTO(
            product.getId(),
            product.getProductCode(),
            product.getProductName(),
            product.getBrand(),
            product.getCategory() != null ? product.getCategory().getId() : null,
            product.getCategory() != null ? product.getCategory().getCategoryName() : null,
            product.getUserType(),
            product.getMaterial(),
            product.getPrice(),
            product.getIsFeatured(),
            imageDTOs
        );
    }

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductCode(productDTO.getProductCode());
        product.setProductName(productDTO.getProductName());
        product.setBrand(productDTO.getBrand());
        // Lấy category từ id (nếu có)
        if (productDTO.getCategoryId() != null) {
            try {
                Long catId = Long.valueOf(productDTO.getCategoryId());
                var optionalCategory = categoryRepository.findById(catId);
                if (optionalCategory.isPresent()) {
                    product.setCategory(optionalCategory.get());
                } else {
                    throw new IllegalArgumentException("Category not found with id: " + productDTO.getCategoryId());
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid categoryId: " + productDTO.getCategoryId());
            }
        }
        product.setUserType(productDTO.getUserType());
        product.setMaterial(productDTO.getMaterial());
        product.setPrice(productDTO.getPrice());
        product.setIsFeatured(productDTO.getIsFeatured());
        product.setStatus(1);
        // Xử lý images nếu có
        if (productDTO.getImageUrls() != null && !productDTO.getImageUrls().isEmpty()) {
            Set<Image> images = productDTO.getImageUrls().stream().map(imgDto -> {
                Image img = new Image();
                img.setImageUrl(imgDto.getImageUrl());
                img.setStatus(1);
                img.setProduct(product);
                return img;
            }).collect(java.util.stream.Collectors.toSet());
            product.setImages(images);
        }
        Product newProduct = productRepository.save(product);
        if (newProduct.getImages() != null) {
            newProduct.getImages().forEach(img -> {
                img.setProduct(newProduct);
                img.setStatus(1);
            });
            imageRepository.saveAll(newProduct.getImages());
        }
        return newProduct;
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setProductCode(productDTO.getProductCode());
            product.setProductName(productDTO.getProductName());
            product.setBrand(productDTO.getBrand());
            // Lấy category từ id (nếu có)
            if (productDTO.getCategoryId() != null) {
                try {
                    Long catId = Long.valueOf(productDTO.getCategoryId());
                    var optionalCategory = categoryRepository.findById(catId);
                    if (optionalCategory.isPresent()) {
                        product.setCategory(optionalCategory.get());
                    } else {
                        throw new IllegalArgumentException("Category not found with id: " + productDTO.getCategoryId());
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid categoryId: " + productDTO.getCategoryId());
                }
            }
            product.setUserType(productDTO.getUserType());
            product.setMaterial(productDTO.getMaterial());
            product.setPrice(productDTO.getPrice());
            product.setIsFeatured(productDTO.getIsFeatured());
            // Xử lý images nếu có (chỉ cập nhật repo, không thêm vào product)
            if (productDTO.getImageUrls() != null) {
                // Soft delete all old images
                if (product.getImages() != null) {
                    for (Image img : product.getImages()) {
                        img.setStatus(0);
                    }
                    imageRepository.saveAll(product.getImages());
                }
                // Chỉ lưu các ảnh có status = 1 từ DTO vào repo
                for (ImageDTO imgDto : productDTO.getImageUrls()) {
                    if (imgDto.getStatus() == 1) {
                        Image img;
                        if (imgDto.getId() != null && imageRepository.existsById(imgDto.getId())) {
                            img = imageRepository.findById(imgDto.getId()).orElse(new Image());
                        } else {
                            img = new Image();
                        }
                        img.setImageUrl(imgDto.getImageUrl());
                        img.setStatus(1);
                        img.setProduct(product);
                        imageRepository.save(img);
                    }
                }
            }
            Product updated = productRepository.save(product);
            return updated;
        }
        return null;
    }

    @Override
    public boolean softDeleteProduct(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setStatus(0);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.map(this::toProductDTO).orElse(null);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findAllActive();
    }

    @Override
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findLowStockProducts(threshold);
    }
}
