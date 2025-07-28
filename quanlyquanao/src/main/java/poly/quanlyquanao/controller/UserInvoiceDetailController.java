package poly.quanlyquanao.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;

@RestController
@RequestMapping("/api/userinvoices/{invoice_code}")
@CrossOrigin(origins = "*")
public class UserInvoiceDetailController {
    @Autowired
    private IInvoiceDetailService invoiceDetailService;
    @GetMapping("/detail")
    public ResponseEntity<?> getInvoiceDetails(@PathVariable("invoice_code") String invoiceCode) {
        try {
            List<InvoiceDetailDTO> details = invoiceDetailService.findAllInvoiceDetaiByInvoiceID(invoiceCode);
            return ResponseEntity.ok(details);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/invoice")
    public ResponseEntity<?> getInvoiceByCode(@PathVariable("invoice_code") String invoiceCode) {
        try {
            InvoiceDTO invoice = invoiceDetailService.getInvoiceByCode(invoiceCode);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/cancel")
    public ResponseEntity<?> updateInvoiceStatus(@PathVariable("invoice_code") String invoiceCode) {
        try {
        	invoiceDetailService.userCancelInvoice(invoiceCode);
            return new ResponseEntity<>("Hủy hóa đơn thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Không tìm thấy hóa đơn với id là " + invoiceCode, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/address")
    public ResponseEntity<?> updateInvoiceAddress(@PathVariable("invoice_code") String invoiceCode,
                                                  @RequestParam("address") String address,
                                                  @RequestParam("phone") String phone) {
        try {
            Invoice updated = invoiceDetailService.updateInvoiceAddress(invoiceCode, address, phone);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/vnpay-success")
    public ResponseEntity<?> updatePaymentMethod(@PathVariable("invoice_code") String invoiceCode) {
        try {
            Invoice updated = invoiceDetailService.updatePaymentMethod(invoiceCode);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
