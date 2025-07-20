package poly.quanlyquanao.mapper;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;

public class InvoiceMapper {
    public static InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) return null;
        String imageFirstProduct = null;
        if (invoice.getInvoiceDetails() != null && !invoice.getInvoiceDetails().isEmpty()) {
            InvoiceDetail firstDetail = invoice.getInvoiceDetails().iterator().next();
            if (firstDetail != null && firstDetail.getProductDetail() != null) {
                imageFirstProduct = firstDetail.getProductDetail().getImgUrl();
            }
        }
        return new InvoiceDTO(
            invoice.getId(),
            invoice.getInvoiceCode(),
            invoice.getUser() != null ? invoice.getUser().getId() : null,
            invoice.getFullname(),
            invoice.getPhonenumber(),
            invoice.getEmail(),
            invoice.getDeliveryAddress(),
            invoice.getPaymentMethod() != null ? invoice.getPaymentMethod().getId() : null,
            invoice.getCreationDate(),
            invoice.getShippingFee(),
            invoice.getVoucher() != null ? invoice.getVoucher().getId() : null,
            invoice.getDiscountAmount(),
            invoice.getTotalAmount(),
            invoice.getAssignedStaff() != null ? invoice.getAssignedStaff().getId() : null,
            imageFirstProduct,
            invoice.getStatus()
        );
    }
}
