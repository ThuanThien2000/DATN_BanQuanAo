package poly.quanlyquanao.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.service.InvoiceDetailService;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;

@RestController
@RequestMapping("/api/invoice/{invoice_code}/detail")
@CrossOrigin(origins = "*")
public class InvoiceDetailController {
	@Autowired
	IInvoiceDetailService invoiceDetailService;
	@GetMapping("")
	public List<InvoiceDetailDTO> getAllByInvoiceID(@PathVariable("invoice_code") String invoiceCode) {
		return invoiceDetailService.findAllInvoiceDetaiByInvoiceID(invoiceCode);
	}
<<<<<<< HEAD
<<<<<<< HEAD
	@GetMapping("/{id}")
	public ResponseEntity getInvoiceDetailById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailById(id));
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
=======
=======
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
>>>>>>> c643860c663e2524b5efbea34166a22e4699254f
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceDetailById(
            @PathVariable("invoice_code") String invoiceCode,
            @PathVariable("id") Long detailId) {
        try {
            InvoiceDetailDTO detail = invoiceDetailService.getInvoiceDetailById(invoiceCode, detailId);
            return ResponseEntity.ok(detail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
<<<<<<< HEAD
}
>>>>>>> 8209aca87929f397e707cf5d57becc5441c79678
=======
    @PutMapping("/status")
    public ResponseEntity<?> updateInvoiceStatus(
            @PathVariable("invoice_code") String invoiceCode,
            @RequestParam("status") Integer status) {
            Invoice updated = invoiceDetailService.updateInvoiceStatus(invoiceCode, status);
            return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
>>>>>>> c643860c663e2524b5efbea34166a22e4699254f
