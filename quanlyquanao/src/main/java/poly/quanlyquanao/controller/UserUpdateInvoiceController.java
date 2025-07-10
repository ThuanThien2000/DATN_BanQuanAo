package poly.quanlyquanao.controller;

import java.util.List;

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

import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;
import poly.quanlyquanao.service.Impl.IUserUpdateInvoiceImpl;

@RestController
@RequestMapping("/api/user/invoice/{invoiceId}/show_update")
@CrossOrigin(origins = "*")
public class UserUpdateInvoiceController {
	@Autowired
    private IUserUpdateInvoiceImpl updateInvoiceService;
    @GetMapping("")
    public ResponseEntity<?> getUserInvoiceUpdate(@PathVariable("invoiceId") Long invoiceId){
    	return ResponseEntity.ok(updateInvoiceService.getInvoiceById(invoiceId));
    }
    @PutMapping("/cancel")
    public ResponseEntity<?> updateInvoiceStatus(@PathVariable("invoiceId") Long invoiceId) {
        try {
        	updateInvoiceService.userCancelInvoice(invoiceId);
            return new ResponseEntity<>("Hủy hóa đơn thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Không tìm thấy hóa đơn với id là " + invoiceId, HttpStatus.NOT_FOUND);
        }
    }
}
