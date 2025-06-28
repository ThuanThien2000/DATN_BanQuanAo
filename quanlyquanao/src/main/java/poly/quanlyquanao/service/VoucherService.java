package poly.quanlyquanao.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.quanlyquanao.model.Voucher;
import poly.quanlyquanao.repository.VoucherRepository;
import poly.quanlyquanao.service.Impl.IVoucherService;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService implements IVoucherService{
	@Autowired
	VoucherRepository voucherRepository;
	
	@Override
	public List<Voucher> getAllVouchers(){
		return voucherRepository.findAll();
	}

	@Override
	public Voucher addVoucher(Voucher voucher) {
		return voucherRepository.save(voucher);
	}

	@Override
	public Voucher updateVoucher(Long id, Voucher updateVoucher) {
		// TODO Auto-generated method stub
		if(voucherRepository.findById(id).isPresent()) {
			return voucherRepository.save(updateVoucher);
		}
		else {
			System.out.println("Voucher with id " + id + " does not exist.");
		}
		return null;
	}

	@Override
	public void deleteVoucher(Long id) {
		// TODO Auto-generated method stub
		if(voucherRepository.findById(id).isPresent()) {
			voucherRepository.deleteById(id);;
		}
		else {
			System.out.println("Voucher with id " + id + " does not exist.");
		}
	}

	@Override
	public Voucher getVoucherById(Long id) {
		// TODO Auto-generated method stub
		return voucherRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Không tìm thấy voucher với id: "+ id));
	}

	@Override
	public List<Voucher> getAllActiveVoucher() {
		// TODO Auto-generated method stub
		return voucherRepository.findActiveVoucher();
	}

	@Override
	public Voucher solfDeleteVoucher(Long id) {
		// TODO Auto-generated method stub
		Voucher inactiveVoucher = getVoucherById(id);
		inactiveVoucher.setStatus(0);
		if(voucherRepository.findById(id).isPresent()) {
			return voucherRepository.save(inactiveVoucher);
		}
		else {
			System.out.println("Voucher with id " + id + " does not exist.");
			return null;
		}
	}
}
