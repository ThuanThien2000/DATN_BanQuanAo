package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.ImageDTO;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.model.Image;
import poly.quanlyquanao.model.Product;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product) {
        Set<ImageDTO> imageDTOs = null;
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            imageDTOs = product.getImages().stream()
                .filter(img -> img.getStatus() == 1)
                .map(img -> new ImageDTO(img.getId(), img.getImageUrl(), img.getStatus()))
                .collect(Collectors.toSet());
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
            product.getDescription(),
            product.getPrice(),
            product.getIsFeatured(),
            imageDTOs,
            product.getStatus()
        );
    }
}
