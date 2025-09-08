package poly.quanlyquanao.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;

@RestController
@RequestMapping("/api/invoice")
@CrossOrigin(origins = "*")
public class InvoiceController {
	@Autowired
    private IInvoiceServiceImpl invoiceService;

    // Get all invoices
    @GetMapping("/all")
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoice();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable("id") Long id){
    	return ResponseEntity.ok(invoiceService.getInvoiceById(id));
    }
    @PutMapping("/{id}/update-status")
    public ResponseEntity<?> updateInvoiceStatus(@PathVariable("id") Long id, @RequestParam("status") Integer status) {
        try {
            invoiceService.updateInvoiceStatus(id, status);
            return new ResponseEntity<>("Cập nhật trạng thái hóa đơn thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Không tìm thấy hóa đơn với id là " + id, HttpStatus.NOT_FOUND);
        }
    }
}