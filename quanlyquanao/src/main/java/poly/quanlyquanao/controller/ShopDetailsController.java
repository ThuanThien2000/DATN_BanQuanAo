package poly.quanlyquanao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.StyleDTO;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ShopDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/shop/{productId}")
@CrossOrigin(origins = "*")
public class ShopDetailsController {
	@Autowired
	ShopDetailsService shopDetailsService;
	
	@GetMapping("")
	public List<ProductDetailDTO> getPDByProductId(@PathVariable Long productId){
		return shopDetailsService.findByProductId(productId);
	}
	@GetMapping("/product")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
		ProductDTO productDTO = shopDetailsService.findProductById(productId);
		return productDTO != null ? ResponseEntity.ok(productDTO) : ResponseEntity.notFound().build();
	}
	@GetMapping("/filter")
	public ProductDetailDTO getSelectedProductDetail(
	    @PathVariable Long productId,
	    @RequestParam String size,
	    @RequestParam String style
	) {
	    return shopDetailsService.findSelectedProductDetail(productId, size, style);
	}
	@GetMapping("/styles")
	public ResponseEntity<List<StyleDTO>> getUniqueStyles(@PathVariable Long productId) {
	    List<StyleDTO> uniqueStyles = shopDetailsService.findUniqueStylesByProductId(productId);
	    return ResponseEntity.ok(uniqueStyles);
	}
	@GetMapping("/sizes")
	public ResponseEntity<List<String>> getUniqueSizes(@PathVariable Long productId) {
	    List<String> uniqueSizes = shopDetailsService.findUniqueSizesByProductId(productId);
	    return ResponseEntity.ok(uniqueSizes);
	}
}
