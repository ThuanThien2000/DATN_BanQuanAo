package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "Images") // Tên bảng là Images
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"product"}) // Loại trừ mối quan hệ
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", nullable = false, columnDefinition = "VARCHAR(MAX)")
    private String imageUrl; // Đổi tên trường để khớp với image_url

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Mối quan hệ nhiều-một với Product

    @Column(name = "is_main")
    private Boolean isMain = false; // Mặc định là false (0 trong SQL Server BIT)

    @Column(name = "order_index")
    private Integer orderIndex;

    @Column(name = "status")
    private Integer status;
}
