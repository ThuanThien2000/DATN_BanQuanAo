package poly.quanlyquanao.service.Impl;

import java.util.List;

import poly.quanlyquanao.dto.InvoiceDTO;
import poly.quanlyquanao.dto.InvoiceDetailDTO;
import poly.quanlyquanao.model.Invoice;
public interface IInvoiceDetailService {
<<<<<<< HEAD
<<<<<<< HEAD
	List<InvoiceDetail> findAllInvoiceDetaiByInvoiceID(Long invoiceID); 
<<<<<<< HEAD
	InvoiceDetail getInvoiceDetailById(Long id);
<<<<<<< HEAD
}
=======
}
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
=======
	InvoiceDetail getInvoiceDetailById(Long invoiceId, Long invoiceDetailId);
}
>>>>>>> 8209aca87929f397e707cf5d57becc5441c79678
=======
	List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(Long invoiceID);
	InvoiceDetailDTO getInvoiceDetailById(Long invoiceId, Long invoiceDetailId);
}
>>>>>>> cf39e873d3357a15548bf1d1e23e898620d98661
=======
	List<InvoiceDetailDTO> findAllInvoiceDetaiByInvoiceID(String invoiceCode);
	InvoiceDetailDTO getInvoiceDetailById(String invoiceCode, Long invoiceDetailId);
	InvoiceDTO getInvoiceByCode(String invoiceCode);
	Invoice updateInvoiceStatus(String invoiceCode, Integer status);
}
>>>>>>> c643860c663e2524b5efbea34166a22e4699254f
