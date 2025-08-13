package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.ImageDTO;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.Image;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;

import java.util.List;
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

        List<ProductDetail> productDetails = product.getProductDetails() != null ? product.getProductDetails().stream()
            .filter(pd -> pd.getStatus() == 1)
            .collect(Collectors.toList()) : null;
        List<InvoiceDetail> invoiceDetails = productDetails != null ? productDetails.stream()
            .flatMap(pd -> pd.getInvoiceDetails().stream())
            .filter(id -> id.getStatus() == 1)
            .collect(Collectors.toList()) : null;
        Integer totalSold = invoiceDetails != null ? invoiceDetails.stream()
            .mapToInt(InvoiceDetail::getQuantity)
            .sum() : 0;

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
            totalSold,
            product.getIsFeatured(),
            imageDTOs,
            product.getStatus()
        );
    }
}
