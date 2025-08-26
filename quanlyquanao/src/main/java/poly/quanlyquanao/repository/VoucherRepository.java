package poly.quanlyquanao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import poly.quanlyquanao.model.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long>{
	@Query("SELECT v from Voucher v WHERE v.status = 1")
	List<Voucher> findActiveVoucher();

	@Query("SELECT v from Voucher v WHERE v.voucherCode = ?1")
	Optional<Voucher> findByCode(String code);
}
