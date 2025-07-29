package poly.quanlyquanao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.dto.ProductInfo;
import poly.quanlyquanao.dto.StyleDTO;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ShopDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/shop/{productCode}")
@CrossOrigin(origins = "*")
public class ShopDetailsController {
	@Autowired
	ShopDetailsService shopDetailsService;
	
	@GetMapping("")
	public List<ProductDetailDTO> getPDByProductId(@PathVariable String productCode){
		return shopDetailsService.findByProductCode(productCode);
	}
	@GetMapping("/product")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable String productCode) {
		ProductDTO productDTO = shopDetailsService.findProductById(productCode);
		return productDTO != null ? ResponseEntity.ok(productDTO) : ResponseEntity.notFound().build();
	}
	@GetMapping("/filter")
	public ProductDetailDTO getSelectedProductDetail(
	    @PathVariable String productCode,
	    @RequestParam String size,
	    @RequestParam String style
	) {
	    return shopDetailsService.findSelectedProductDetail(productCode, size, style);
	}
	@GetMapping("/styles")
	public ResponseEntity<List<StyleDTO>> getUniqueStyles(@PathVariable String productCode) {
	    List<StyleDTO> uniqueStyles = shopDetailsService.findUniqueStylesByProductId(productCode);
	    return ResponseEntity.ok(uniqueStyles);
	}
	@GetMapping("/sizes")
	public ResponseEntity<List<String>> getUniqueSizes(@PathVariable String productCode) {
	    List<String> uniqueSizes = shopDetailsService.findUniqueSizesByProductId(productCode);
	    return ResponseEntity.ok(uniqueSizes);
	}
	@GetMapping("/related")
	public ResponseEntity<List<ProductInfo>> getRelatedProducts(@PathVariable String productCode) {
		List<ProductInfo> relatedProducts = shopDetailsService.findRelatedProductsByCategoryId(productCode);
		return ResponseEntity.ok(relatedProducts);
	}
}
