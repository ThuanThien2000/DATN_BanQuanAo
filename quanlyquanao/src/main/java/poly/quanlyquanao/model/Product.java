package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Product")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString(exclude = {"category", "productDetails", "reviews", "images"}) // Loại trừ các mối quan hệ để tránh lỗi thông báo
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_code", nullable = false, unique = true, length = 50)
    private String productCode;

    @Column(name = "product_name", nullable = false, columnDefinition = "NVARCHAR(MAX)")
    private String productName;

    @Column(nullable = false, length = 50)
    private String brand;

    @ManyToOne // Mối quan hệ nhiều-một với Category
    @JoinColumn(name = "category_id", nullable = false) // Khóa ngoại tới bảng Category
    private Category category; // Thay thế 'category' String bằng đối tượng Category

    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

    @Column(nullable = false, length = 50)
    private String material;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    private Integer status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images; // Mối quan hệ với bảng Images mới


    public Product(Long id, String productCode, String productName, String brand, Category category, String userType,
			String material, String description, BigDecimal price, Boolean isFeatured, Integer status,
			Set<ProductDetail> productDetails, Set<Review> reviews, Set<Image> images) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.brand = brand;
		this.category = category;
		this.userType = userType;
		this.material = material;
		this.description = description;
		this.price = price;
		this.isFeatured = isFeatured;
		this.status = status;
		this.productDetails = productDetails;
		this.reviews = reviews;
		this.images = images;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public Product() {}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(Set<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
