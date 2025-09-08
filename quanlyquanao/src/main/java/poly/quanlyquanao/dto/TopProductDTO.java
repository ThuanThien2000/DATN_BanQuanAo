package poly.quanlyquanao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopProductDTO {
    private Long productId;
    private String productName;
    private Long quantitySold;
    private Double totalRevenue;
	public TopProductDTO(Long productId, String productName, Long quantitySold, Double totalRevenue) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantitySold = quantitySold;
		this.totalRevenue = totalRevenue;
	}
    
    
}