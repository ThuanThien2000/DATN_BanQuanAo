package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.model.Image;
import java.util.Objects;

public class InvoiceDetailMapper {
    public static InvoiceDetailDTO toDTO(InvoiceDetail entity) {
        if (entity == null) return null;
        ProductDetail pd = entity.getProductDetail();
        Product product = (pd != null) ? pd.getProduct() : null;
        String imgUrl = null;
        if (pd != null && pd.getImgUrl() != null && !pd.getImgUrl().isEmpty()) {
            imgUrl = pd.getImgUrl();
        } else if (product != null && product.getImages() != null && !product.getImages().isEmpty()) {
            // Lấy ảnh đầu tiên của product nếu productDetail không có ảnh
            Image firstImg = product.getImages().stream()
                .filter(img -> Objects.equals(img.getStatus(), 1))
                .findFirst().orElse(null);
            if (firstImg != null) {
                imgUrl = firstImg.getImageUrl();
            }
        }
        return new InvoiceDetailDTO(
            entity.getId(),
            entity.getInvoice() != null ? entity.getInvoice().getInvoiceCode() : null,
            pd != null ? pd.getId() : null,
            product != null ? product.getId() : null,
            product != null ? product.getProductName() : null,
            pd != null ? pd.getProductDetailCode() : null,
            imgUrl,
            pd != null ? pd.getSize() : null,
            pd != null ? pd.getStyle() : null,
            entity.getQuantity(),
            entity.getPrice(),
            entity.getTotalPrice(),
            entity.getStatus()
        );
    }
}
