package poly.quanlyquanao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.quanlyquanao.model.InvoiceDetail;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long>{
<<<<<<< HEAD

=======
>>>>>>> 24e6ed3616444da2659de8f5147ef376a3049ed9
	List<InvoiceDetail> findAllByInvoice_IdAndStatus(Long invoiceId, int status);
	Optional<InvoiceDetail> findByInvoice_IdAndId(Long invoiceId, Long invoiceDetailId);
}
