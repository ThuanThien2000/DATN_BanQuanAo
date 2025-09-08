package poly.quanlyquanao.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;

@RestController
@RequestMapping("/api/admin/invoice/{invoice_id}/detail")
@CrossOrigin(origins = "*")
public class InvoiceDetailController {
	@Autowired
	IInvoiceDetailService invoiceDetailService;
	@GetMapping("")
	public List<InvoiceDetail> getAllByInvoiceID(@PathVariable("invoice_id") Long invoiceId) {
		return invoiceDetailService.findAllInvoiceDetaiByInvoiceID(invoiceId);
	}
	@GetMapping("/{id}")
	public ResponseEntity getInvoiceDetailById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(invoiceDetailService.getInvoiceDetailById(id));
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
