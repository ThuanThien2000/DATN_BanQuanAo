package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;
import poly.quanlyquanao.repository.InvoiceRepository;
@Service
public class InvoiceService implements IInvoiceServiceImpl{
    @Autowired
    private InvoiceRepository invoiceRepository;
	@Override
	public List<Invoice> getAllInvoice() {
		// TODO Auto-generated method stub
		return invoiceRepository.findAll();
	}

	@Override
	public List<Invoice> getTodayInvoice() {
		// TODO Auto-generated method stub
		return invoiceRepository.findTodayInvoices();
	}

	@Override
	public void updateInvoiceStatus(Long id, Integer status) {
	    Invoice invoice = invoiceRepository.findById(id).orElse(null);

	    if (invoice != null) {
	        invoice.setStatus(status);
	        invoiceRepository.save(invoice);
	    } else {
	        System.out.println("Invoice with id " + id + " does not exist.");
	    }
	}
}
