package poly.quanlyquanao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LowSellingProductDTO {
    private Long productId;
    private String productName;
    private Integer quantitySoldLastXDays;
    private Integer stockQuantity;
	public LowSellingProductDTO(Long productId, String productName, Integer quantitySoldLastXDays,
			Integer stockQuantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantitySoldLastXDays = quantitySoldLastXDays;
		this.stockQuantity = stockQuantity;
	}
    
    
}