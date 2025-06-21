package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "Product_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString(exclude = {"product"}) // Loại trừ mối quan hệ
public class ProductDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_detail_code", nullable = false, unique = true, length = 50)
    private String productDetailCode;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, length = 50)
    private String color;

    @Column(nullable = false, length = 50)
    private String size;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "inventory_quantity", nullable = false)
    private Integer inventoryQuantity = 0;

    @Column(name = "imgUrl", columnDefinition = "VARCHAR(MAX)")
    private String imgUrl;

    private Integer status;
}
