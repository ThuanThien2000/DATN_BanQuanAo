package poly.quanlyquanao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.quanlyquanao.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
	List<PaymentMethod> findByStatus(Integer status);
}
