package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "Images") // Tên bảng là Images
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString(exclude = {"product"}) // Loại trừ mối quan hệ
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

    public Image(String imageUrl, Product product, Boolean isMain, Integer orderIndex) {
        this.imageUrl = imageUrl;
        this.product = product;
        this.isMain = isMain;
        this.orderIndex = orderIndex;
    }
    public Image() {}

    public Long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public Boolean getMain() {
        return isMain;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}
