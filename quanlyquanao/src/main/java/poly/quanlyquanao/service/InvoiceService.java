package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.mapper.InvoiceMapper;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;
import poly.quanlyquanao.repository.InvoiceRepository;
@Service
public class InvoiceService implements IInvoiceServiceImpl{
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Override
	public java.util.List<InvoiceDTO> getAllInvoice() {
		return invoiceRepository.findAll().stream().map(InvoiceMapper::toDTO).toList();
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

	@Override
	public InvoiceDTO getInvoiceById(Long id) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy Invoice với id: "+ id));
		return InvoiceMapper.toDTO(invoice);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
