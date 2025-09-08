package poly.quanlyquanao.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProductInfo {
    private String productCode;
    private String productName;
    private String userType;
    private Long categoryId;
    private BigDecimal price;
    private Boolean isFeatured;
    private String imageUrls;
    private Set<String> sizes;
    private Set<String> styles;

    public ProductInfo() {}

    public ProductInfo(String productCode, String productName, String userType, Long categoryId, BigDecimal price, Boolean isFeatured, String imageUrls, Set<String> sizes, Set<String> styles) {
        this.productCode = productCode;
        this.productName = productName;
        this.userType = userType;
        this.categoryId = categoryId;
        this.price = price;
        this.isFeatured = isFeatured;
        this.imageUrls = imageUrls;
        this.sizes = sizes;
        this.styles = styles;
    }

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Boolean getIsFeatured() { return isFeatured; }
    public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
    public String getImageUrls() { return imageUrls; }
    public void setImageUrls(String imageUrls) { this.imageUrls = imageUrls; }
    public Set<String> getSizes() { return sizes; }
    public void setSizes(Set<String> sizes) { this.sizes = sizes; }
    public Set<String> getStyles() { return styles; }
    public void setStyles(Set<String> styles) { this.styles = styles; }
}
