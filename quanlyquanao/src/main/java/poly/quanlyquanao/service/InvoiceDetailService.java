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
	public List<InvoiceDetail> findAllInvoiceDetaiByProductID(Long productID) {
		// TODO Auto-generated method stub
		return invoiceDetailRepository.findAllByStatusAndProduct_Id(1, productID);
	}

	@Override
	public InvoiceDetail getInvoiceDetailById(Long id) {
	    return invoiceDetailRepository.findById(id)
	    		.orElseThrow(() -> new RuntimeException("Không tìm thấy InvoiceDetail với id: "+ id));
	}

	@Override
	public void solfDeleteInvoiceDetail(Long id) {
	    if (invoiceDetailRepository.findById(id).isPresent()) {
	        invoiceDetailRepository.deleteById(id);
	    } else {
	        System.out.println("Invoice detail với id " + id + " không tồn tại.");
	    }
	}


}
