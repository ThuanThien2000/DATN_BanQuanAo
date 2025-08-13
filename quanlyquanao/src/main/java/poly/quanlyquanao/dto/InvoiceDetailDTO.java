package poly.quanlyquanao.dto;

import java.math.BigDecimal;


public class InvoiceDetailDTO {
    private Long id;
    private String invoiceCode;
    private Long productDetailId;
    private Long productId;
    private String productName;
    private String productDetailCode;
    private String imgUrl;
    private String size;
    private String style;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Integer status;


    public InvoiceDetailDTO() {}

    public InvoiceDetailDTO(Long id, String invoiceCode, Long productDetailId, Long productId, String productName, String productDetailCode, String imgUrl, String size, String style, Integer quantity, BigDecimal price, BigDecimal totalPrice, Integer status) {
        this.id = id;
        this.invoiceCode = invoiceCode;
        this.productDetailId = productDetailId;
        this.productId = productId;
        this.productName = productName;
        this.productDetailCode = productDetailCode;
        this.imgUrl = imgUrl;
        this.size = size;
        this.style = style;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInvoiceCode() { return invoiceCode; }
    public void setInvoiceCode(String invoiceCode) { this.invoiceCode = invoiceCode; }
    public Long getProductDetailId() { return productDetailId; }
    public void setProductDetailId(Long productDetailId) { this.productDetailId = productDetailId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductDetailCode() { return productDetailCode; }
    public void setProductDetailCode(String productDetailCode) { this.productDetailCode = productDetailCode; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
