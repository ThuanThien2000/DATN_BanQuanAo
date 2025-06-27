package poly.quanlyquanao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Images {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String url;

	public Images() {
		super();
	}

	public Images(Long id, Product product, String url) {
		super();
		this.id = id;
		this.product = product;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
