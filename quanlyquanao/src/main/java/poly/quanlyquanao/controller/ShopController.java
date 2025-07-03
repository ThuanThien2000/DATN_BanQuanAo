package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.ProductDetail;
import poly.quanlyquanao.service.ShopService;

@RestController
@RequestMapping("/api/customer/product-details")
public class ShopController {
	@Autowired
    private ShopService shopService;
	
	@GetMapping("/filter")
    public Page<ProductDetail> filter(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String style,
            @RequestParam(defaultValue = "0") int page
    ) {
        return shopService.filter(categoryId, minPrice, maxPrice, size, style, page);
    }
}
