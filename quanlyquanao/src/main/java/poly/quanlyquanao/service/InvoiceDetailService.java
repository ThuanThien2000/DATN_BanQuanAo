package poly.quanlyquanao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.model.InvoiceDetail;
import poly.quanlyquanao.repository.InvoiceDetailRepository;
import poly.quanlyquanao.service.Impl.IInvoiceDetailService;
@Service
public class InvoiceDetailService implements IInvoiceDetailService{
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	@Override
	public List<InvoiceDetail> findAllInvoiceDetaiByInvoiceID(Long invoiceID) {
		// TODO Auto-generated method stub
		return invoiceDetailRepository.findAllByInvoice_IdAndStatus(invoiceID,1);
	}

	@Override
	public InvoiceDetail getInvoiceDetailById(Long id) {
	    return invoiceDetailRepository.findById(id)
	    		.orElseThrow(() -> new RuntimeException("Không tìm thấy InvoiceDetail với id: "+ id));
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
