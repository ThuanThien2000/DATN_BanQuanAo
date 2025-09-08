package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.mapper.InvoiceMapper;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/userinvoices")
@CrossOrigin(origins = "*")
public class UserInvoiceController {
    @Autowired
    private IInvoiceServiceImpl invoiceService;

    //  http://localhost:8080/api/userinvoices
    @GetMapping
    public ResponseEntity<?> getMyOrders(Principal principal) {
        List<Invoice> invoices = invoiceService.getInvoicesByCurrentUser(principal.getName());
        List<InvoiceDTO> result = invoices.stream().map(InvoiceMapper::toDTO).toList();
        return ResponseEntity.ok(result);
    }

}
