package poly.quanlyquanao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDTO {
    private Integer status;
    private Long count;
	public OrderStatusDTO(Integer status, Long count) {
		super();
		this.status = status;
		this.count = count;
	}
    
    
}