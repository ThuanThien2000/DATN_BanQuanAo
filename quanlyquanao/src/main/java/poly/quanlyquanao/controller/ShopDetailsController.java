package poly.quanlyquanao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ShopDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/client/shop/{productId}")
@CrossOrigin(origins = "*")
public class ShopDetailsController {
	@Autowired
	ShopDetailsService shopDetailsService;
	
	@GetMapping("")
	public List<ProductDetail> getPDByProductId(@PathVariable Long productId){
		return shopDetailsService.findByProductId(productId);
	}
	@GetMapping("/filter")
	public ProductDetail getSelectedProductDetail(
	    @PathVariable Long productId,
	    @RequestParam String size,
	    @RequestParam String style
	) {
	    return shopDetailsService.findSelectedProductDetail(productId, size, style);
	}
}
