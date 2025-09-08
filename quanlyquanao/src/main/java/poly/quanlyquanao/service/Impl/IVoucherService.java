package poly.quanlyquanao.service.Impl;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import poly.quanlyquanao.model.Voucher;
public interface IVoucherService {
	Voucher getVoucherByCode(String code);
	List<Voucher> getAllVouchers();
	List<Voucher> getAllActiveVoucher();
	Voucher solfDeleteVoucher(Long id);
	Voucher getVoucherById(Long id);
	Voucher addVoucher(Voucher voucher);
	Voucher updateVoucher(Long id, Voucher updateVoucher);
	void deleteVoucher(Long id);
}
