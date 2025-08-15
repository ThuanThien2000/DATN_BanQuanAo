package poly.quanlyquanao.dto;

import java.math.BigDecimal;

public class ProductDetailDTO {
    private Long id;
    private String productDetailCode;
    private String productCode;
    private String productName;
    private BigDecimal productPrice;
    private String style;
    private String size;
    private Integer inventoryQuantity;
    private String imgUrl;
    private Integer status;


    public ProductDetailDTO() {}

    public ProductDetailDTO(Long id, String productDetailCode, String productCode, String productName, BigDecimal productPrice, String style, String size, Integer inventoryQuantity, String imgUrl, Integer status) {
        this.id = id;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.style = style;
        this.size = size;
        this.inventoryQuantity = inventoryQuantity;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductDetailCode() { return productDetailCode; }
    public void setProductDetailCode(String productDetailCode) { this.productDetailCode = productDetailCode; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    // Đã thay thế productId bằng productCode
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public BigDecimal getProductPrice() { return productPrice; }
    public void setProductPrice(BigDecimal productPrice) { this.productPrice = productPrice; }
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public Integer getInventoryQuantity() { return inventoryQuantity; }
    public void setInventoryQuantity(Integer inventoryQuantity) { this.inventoryQuantity = inventoryQuantity; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
