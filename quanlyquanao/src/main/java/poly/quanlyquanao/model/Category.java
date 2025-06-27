package poly.quanlyquanao.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set; // Dùng Set cho mối quan hệ OneToMany

@Entity
@Table(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"products"}) // Loại trừ mối quan hệ để tránh StackOverflowError
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
    private String categoryName;

    @Column(name = "description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "status")
    private Integer status;

    // Mối quan hệ một-nhiều với Product (một Category có nhiều Products)
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;
}
