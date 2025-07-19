package poly.quanlyquanao.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.CartItem;
import poly.quanlyquanao.dto.CheckoutRequest;
import poly.quanlyquanao.dto.InvoiceInfo;
import poly.quanlyquanao.dto.ProductDTO;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.ICheckoutServiceImpl;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {
    @Autowired
    private ICheckoutServiceImpl checkoutService;
    @GetMapping("/get-product-detail")
    public ResponseEntity<?> getProductDetailByCode(@RequestParam String productDetailCode) {
        try {
            ProductDetailDTO productDetailDTO = checkoutService.getProductDetailDTOByPDCode(productDetailCode);
            if (productDetailDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Product not found"));
            }
            return ResponseEntity.ok(productDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {
        try {
            InvoiceInfo getInvoiceInfo = request.getInvoiceInfo();
            List<CartItem> details = request.getItems();

            // ✅ Dùng biến đã khai báo thay vì class
            Invoice savedInvoice = checkoutService.checkoutOrder(getInvoiceInfo, details);
            return ResponseEntity.ok(savedInvoice);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
//{
//	  "invoiceInfo": {
//	    "fullname": "Nguyễn Văn A",
//	    "phonenumber": "0901234567",
//	    "email": "nguyenvana@example.com",
//	    "deliveryAddress": "123 Đường Lê Lợi, Quận 1, TP.HCM",
//	    "paymentMethodId": 1,
//	    "voucherId": 2,
//	    "assignedStaffId": 5
//	  },
//	  "items": [
//	    {
//	      "productDetailId": 1,
//	      "quantity": 2
//	    },
//	    {
//	      "productDetailId": 3,
//	      "quantity": 1
//	    }
//	  ]
//	}
