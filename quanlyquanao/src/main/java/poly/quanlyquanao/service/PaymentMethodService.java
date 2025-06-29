package poly.quanlyquanao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.PaymentMethod;
import poly.quanlyquanao.repository.PaymentMethodRepository;

import java.util.List;

@Service
public class PaymentMethodService {
	
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	public List<PaymentMethod> getAll() {
		return paymentMethodRepository.findAll();
	}
	
	public PaymentMethod getById(Long id) {
		return paymentMethodRepository.findById(id).orElse(null);
	}
	
	public PaymentMethod create (PaymentMethod paymentMethod ) {
		paymentMethod.setStatus(1);
		return paymentMethodRepository.save(paymentMethod);
	}
	
	public PaymentMethod update (Long id, PaymentMethod update) {
		PaymentMethod existing  = paymentMethodRepository.findById(id).orElse(null);
		if (existing != null) {
			existing.setNamePaymentMethod(update.getNamePaymentMethod());
			existing.setStatus(update.getStatus());
			return paymentMethodRepository.save(existing);
		}
		return null;
	}
	
	public void hide(Long id) {
		PaymentMethod existing = paymentMethodRepository.findById(id).orElse(null);
		if (existing != null) {
			existing.setStatus(0);
			paymentMethodRepository.save(existing);
		}
	}
	
	public List<PaymentMethod> getAllActive() {
		return paymentMethodRepository.findByStatus(1);
	}
}
