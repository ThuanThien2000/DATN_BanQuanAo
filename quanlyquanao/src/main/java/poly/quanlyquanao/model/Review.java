package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Review", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "product_id"})
})
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "Rating", nullable = false)
    private Integer rating;

    @Column(name = "Comment", columnDefinition = "NVARCHAR(MAX)")
    private String comment;

    @Column(name = "review_date")
    private LocalDateTime reviewDate = LocalDateTime.now();

    @Column(name = "Status")
    private Integer status = 1;

    public Review(User user, Product product, Integer rating, String comment, LocalDateTime reviewDate, Integer status) {
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.status = status;
    }
    public Review() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}