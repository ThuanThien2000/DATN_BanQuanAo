package poly.quanlyquanao.dto;

import poly.quanlyquanao.model.Invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserInvoiceDTO {
    private String invoiceCode;
    private LocalDateTime creationDate;
    private BigDecimal totalAmount;
    private String description;

    public UserInvoiceDTO(Invoice invoice) {
        this.invoiceCode = invoice.getInvoiceCode();
        this.creationDate = invoice.getCreationDate();
        this.totalAmount = invoice.getTotalAmount();
        this.description = invoice.getDescription();
    }

    public UserInvoiceDTO() {
    }

    public UserInvoiceDTO(String invoiceCode, LocalDateTime creationDate, BigDecimal totalAmount, String description) {
        this.invoiceCode = invoiceCode;
        this.creationDate = creationDate;
        this.totalAmount = totalAmount;
        this.description = description;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
