package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "img_url", columnDefinition = "VARCHAR(MAX)")
    private String imgUrl;

    private Integer status;
}
