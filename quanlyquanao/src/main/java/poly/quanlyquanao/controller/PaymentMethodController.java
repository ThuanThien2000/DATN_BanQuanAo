package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poly.quanlyquanao.model.PaymentMethod;
import poly.quanlyquanao.service.PaymentMethodService;

import java.util.List;

@RestController
@RequestMapping("/api/paymentmethods")
public class PaymentMethodController {
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	@GetMapping
	public List<PaymentMethod> getAll() {
		return paymentMethodService.getAll();
	}
	
	@GetMapping("/active")
	public List<PaymentMethod> getAllActive() {
		return paymentMethodService.getAllActive();
	}
	
	@GetMapping("/{id}")
	public PaymentMethod getById (@PathVariable Long id) {
		return paymentMethodService.getById(id);
	}
	
	@PostMapping
	public PaymentMethod create (@RequestBody PaymentMethod paymentMethod) {
		return paymentMethodService.create(paymentMethod);
	}
	
	@PutMapping("/{id}")
	public PaymentMethod update (@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
		return paymentMethodService.update(id, paymentMethod);
	}
	
	@DeleteMapping("/{id}")
	public void hide (@PathVariable Long id) {
		paymentMethodService.hide(id);
	}
}
