package poly.quanlyquanao.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import poly.quanlyquanao.model.Image;
public class ProductDTO {
    private Long id;
    private String productCode;
    private String productName;
    private String brand;
    private Long categoryId;
    private String categoryName;
    private String userType;
    private String material;
    private BigDecimal price;
    private Boolean isFeatured;
    private Set<ImageDTO> imageUrls; // image đầu

    public ProductDTO() {}

    public ProductDTO(Long id, String productCode, String productName, String brand, Long categoryId, String categoryName, String userType, String material, BigDecimal price, Boolean isFeatured, Set<ImageDTO> imageUrls) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.brand = brand;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userType = userType;
        this.material = material;
        this.price = price;
        this.isFeatured = isFeatured;
        this.imageUrls = imageUrls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Set<ImageDTO> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(Set<ImageDTO> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
