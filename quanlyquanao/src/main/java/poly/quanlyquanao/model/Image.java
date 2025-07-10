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

    @Column(name = "status")
    private int status ;

    public Image() {
    }

    public Image(String imageUrl, Product product, int status) {
        this.imageUrl = imageUrl;
        this.product = product;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
