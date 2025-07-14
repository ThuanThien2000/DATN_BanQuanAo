package poly.quanlyquanao.dto;

import java.math.BigDecimal;

public class InvoiceDetailDTO {
    private Long id;
    private Long invoiceId;
    private Long productDetailId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Integer status;

    public InvoiceDetailDTO() {}

    public InvoiceDetailDTO(Long id, Long invoiceId, Long productDetailId, Integer quantity, BigDecimal price, BigDecimal totalPrice, Integer status) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productDetailId = productDetailId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getInvoiceId() { return invoiceId; }
    public void setInvoiceId(Long invoiceId) { this.invoiceId = invoiceId; }
    public Long getProductDetailId() { return productDetailId; }
    public void setProductDetailId(Long productDetailId) { this.productDetailId = productDetailId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
