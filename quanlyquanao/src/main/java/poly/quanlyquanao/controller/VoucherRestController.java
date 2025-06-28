package poly.quanlyquanao.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import poly.quanlyquanao.model.Voucher;
import poly.quanlyquanao.service.Impl.IVoucherService;

@RestController
@RequestMapping("/api/admin/voucher")
@CrossOrigin(origins = "*")
public class VoucherRestController {
	@Autowired
	IVoucherService voucherService;
	
	@GetMapping("/all")
	public List<Voucher> getAllVoucher() {
		return voucherService.getAllVouchers();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addVoucher(@RequestBody Voucher voucher){
		try {
			Voucher saveVoucher = voucherService.addVoucher(voucher);
			return ResponseEntity.ok(saveVoucher);
		} catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateVoucher(@PathVariable("id") Long id,@RequestBody Voucher voucher){
		try {
			voucherService.updateVoucher(id, voucher);
			return ResponseEntity.ok(voucher);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> getVoucherById(@PathVariable("id") Long id){
		return ResponseEntity.ok(voucherService.getVoucherById(id));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteVoucher(@PathVariable("id") Long id){
		try {
			voucherService.solfDeleteVoucher(id);
			return new ResponseEntity<>("Đã xóa voucher thành công!!!", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>("Không tìm thấy voucher với id là " + id, HttpStatus.NOT_FOUND);
		}
	}
}
