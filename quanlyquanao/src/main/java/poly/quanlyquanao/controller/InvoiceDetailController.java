package poly.quanlyquanao.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;

@RestController
@RequestMapping("/api/invoice/{invoice_id}/detail")
@CrossOrigin(origins = "*")
public class InvoiceDetailController {
	@Autowired
	IInvoiceDetailService invoiceDetailService;
	@GetMapping("")
	public List<InvoiceDetailDTO> getAllByInvoiceID(@PathVariable("invoice_id") Long invoiceId) {
		return invoiceDetailService.findAllInvoiceDetaiByInvoiceID(invoiceId);
	}
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceDetailById(
            @PathVariable("invoice_id") Long invoiceId,
            @PathVariable("id") Long detailId) {
        try {
            InvoiceDetailDTO detail = invoiceDetailService.getInvoiceDetailById(invoiceId, detailId);
            return ResponseEntity.ok(detail);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}