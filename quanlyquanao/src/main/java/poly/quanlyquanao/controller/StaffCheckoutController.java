package poly.quanlyquanao.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.quanlyquanao.dto.CartItem;
import poly.quanlyquanao.dto.CheckoutRequest;
import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceInfo;
import poly.quanlyquanao.dto.ProductDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.service.StaffCheckoutService;
import poly.quanlyquanao.service.Impl.IUserService;
import poly.quanlyquanao.service.Impl.IVoucherService;

@RestController
@RequestMapping("/api/staff-checkout")
@CrossOrigin(origins = "*")
public class StaffCheckoutController {
    @Autowired
    private StaffCheckoutService checkoutService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IVoucherService voucherService;

    @GetMapping("/get-voucher")
    public ResponseEntity<?> getVoucher(@RequestParam String code) {
            try {
                return ResponseEntity.ok(voucherService.getVoucherByCode(code));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
            }
    }
    
    @GetMapping("/customer-info")
    public ResponseEntity<?> getCustomerInfo(@RequestParam String phoneNumber){
        try {
            return ResponseEntity.ok(checkoutService.getAccountInfo(phoneNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/get-product-detail")
    public ResponseEntity<?> getProductDetailByCode(@RequestParam String productDetailCode) {
        try {
            ProductDetailDTO productDetailDTO = checkoutService.getProductDetailDTOByPDCode(productDetailCode);
            if (productDetailDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Product not found"));
            }
            if(productDetailDTO.getStatus()==0) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Sản phẩm đã không còn bán"));
            }
            if(productDetailDTO.getInventoryQuantity()<1) {
            	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Sản phẩm đã hết hàng"));
            }
            return ResponseEntity.ok(productDetailDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request,Principal principal) {
        try {
            InvoiceDTO newInvoice = null;
            InvoiceInfo getInvoiceInfo = request.getInvoiceInfo();
            List<CartItem> details = request.getItems();
            if (details == null || details.isEmpty()) {
                return ResponseEntity.badRequest().body("Không tạo được hóa đơn: Giỏ hàng trống");
            }

            // ✅ Dùng biến đã khai báo thay vì class
            Invoice savedInvoice = checkoutService.checkoutOrder(getInvoiceInfo, details);
            if (principal != null) {
                User meUser = userService.getUserByUsername(principal.getName());
                savedInvoice.setUser(meUser);
                newInvoice = checkoutService.add(savedInvoice);
            }
             else {
            // Nếu không đăng nhập thì gán user null hoặc 1 giá trị mặc định
            savedInvoice.setUser(null); 
            // 👉 bạn có thể tạo 1 User "guest" trong DB và gán ở đây nếu muốn
        }
            return ResponseEntity.ok(newInvoice);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}
