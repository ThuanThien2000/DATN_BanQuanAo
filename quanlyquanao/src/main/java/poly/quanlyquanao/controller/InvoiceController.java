package poly.quanlyquanao.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;

@RestController
@RequestMapping("/api/admin/invoice")
@CrossOrigin(origins = "*")
public class InvoiceController {
	@Autowired
    private IInvoiceServiceImpl invoiceService;

    // Get all invoices
    @GetMapping("/all")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoice();
    }

    // Get today's invoices
    @GetMapping("/today")
    public List<Invoice> getTodayInvoices() {
        return invoiceService.getTodayInvoice();
    }
    @GetMapping("/id")
    public ResponseEntity<?> getInvoiceById(@PathVariable("id") Long id){
    	return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
    @PutMapping("/update-status/{id}")
    public ResponseEntity<?> updateInvoiceStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        try {
            invoiceService.updateInvoiceStatus(id, status);
            return new ResponseEntity<>("Cập nhật trạng thái hóa đơn thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Không tìm thấy hóa đơn với id là " + id, HttpStatus.NOT_FOUND);
        }
    }
}
