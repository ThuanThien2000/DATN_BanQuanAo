package poly.quanlyquanao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LowStockProductDTO {
	  private Long productId;
	    private String productName;
	    private Integer inventoryQuantity;

	    public LowStockProductDTO(Long productId, String productName, Integer inventoryQuantity) {
	        this.productId = productId;
	        this.productName = productName;
	        this.inventoryQuantity = inventoryQuantity;
	    }
}
