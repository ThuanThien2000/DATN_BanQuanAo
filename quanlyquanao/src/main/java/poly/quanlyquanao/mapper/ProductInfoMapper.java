package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.model.Product;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductInfoMapper {
    public static ProductInfo toProductInfo(Product product) {
        if (product == null) return null;
        // Lấy danh sách size và style từ ProductDetail nếu có
        Set<String> sizes = null;
        Set<String> styles = null;
        if (product.getProductDetails() != null) {
            sizes = product.getProductDetails().stream()
                    .map(pd -> pd.getSize())
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.toSet());
            styles = product.getProductDetails().stream()
                    .map(pd -> pd.getStyle())
                    .filter(s -> s != null && !s.isEmpty())
                    .collect(Collectors.toSet());
        }
        // Lấy 1 ảnh đầu tiên (status=1)
        String imageUrls = null;
        if (product.getImages() != null && !product.getImages().isEmpty()) {
            imageUrls = product.getImages().stream()
                    .filter(img -> img.getStatus() == 1)
                    .map(img -> img.getImageUrl())
                    .findFirst()
                    .orElse(null);
        }
        return new ProductInfo(
                product.getProductCode(),
                product.getProductName(),
                product.getUserType(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getPrice(),
                product.getIsFeatured(),
                imageUrls,
                sizes,
                styles
        );
    }
}
