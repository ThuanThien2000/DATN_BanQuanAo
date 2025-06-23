package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = {"productDetails", "reviews"}) // Loại trừ các mối quan hệ
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

    @Column(nullable = false, length = 50)
    private String category;

    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

    @Column(nullable = false, length = 50)
    private String material;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "img_url_one", columnDefinition = "VARCHAR(MAX)")
    private String imgUrlOne;
    @Column(name = "img_url_two", columnDefinition = "VARCHAR(MAX)")
    private String imgUrlTwo;
    @Column(name = "img_url_three", columnDefinition = "VARCHAR(MAX)")
    private String imgUrlThree;

    private Integer status;
}
