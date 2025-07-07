package poly.quanlyquanao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.quanlyquanao.model.Invoice;

public interface InvoiceRepository  extends JpaRepository<Invoice,Long>{
	@Query("SELECT i FROM Invoice i ORDER BY i.id DESC")
	List<Invoice> findAllInvoice();
	@Query("SELECT i FROM Invoice i WHERE DATE(i.createdDate) = CURRENT_DATE") // Sai code
	List<Invoice> findTodayInvoices();

}
