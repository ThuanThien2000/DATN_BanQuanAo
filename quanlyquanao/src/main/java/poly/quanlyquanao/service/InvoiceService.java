package poly.quanlyquanao.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.model.Invoice;
import poly.quanlyquanao.mapper.InvoiceMapper;
import poly.quanlyquanao.model.User;
import poly.quanlyquanao.repository.InvoiceRepository;
import poly.quanlyquanao.repository.UserRepository;
import poly.quanlyquanao.service.Impl.IInvoiceServiceImpl;
@Service
public class InvoiceService implements IInvoiceServiceImpl{
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public java.util.List<InvoiceDTO> getAllInvoice() {
		return invoiceRepository.findAll().stream().map(InvoiceMapper::toDTO).toList();
	}


	@Override
	public void updateInvoiceStatus(Long id, Integer status) {
		Invoice invoice = invoiceRepository.findById(id).orElse(null);
		if (invoice != null) {
			if (status == 5) {
			invoice.setPaymentDate(LocalDateTime.now());
		}
			invoice.setStatus(status);
			invoiceRepository.save(invoice);
		} else {
			System.out.println("Invoice with id " + id + " does not exist.");
		}
	}

	@Override
	public List<Invoice> getInvoicesByCurrentUser(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Người dùng không tồn tại"));
		return invoiceRepository.findByUserId(user.getId());
	}

	@Override
	public InvoiceDTO getInvoiceById(Long id) {
		Invoice invoice = invoiceRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy Invoice với id: "+ id));
		return InvoiceMapper.toDTO(invoice);
	}
}