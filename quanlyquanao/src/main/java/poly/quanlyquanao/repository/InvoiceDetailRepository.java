package poly.quanlyquanao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.quanlyquanao.model.InvoiceDetail;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long>{

//	List<InvoiceDetail> findAllByStatusAndProduct_Id(int status, Long productId);
    // Sai code
}
