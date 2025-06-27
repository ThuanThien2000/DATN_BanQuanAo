package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private Integer status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Image> images; // Mối quan hệ với bảng Images mới
}
