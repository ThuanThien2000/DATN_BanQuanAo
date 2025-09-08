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
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.ICheckoutServiceImpl;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {
    @Autowired
    private ICheckoutServiceImpl checkoutService;
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
