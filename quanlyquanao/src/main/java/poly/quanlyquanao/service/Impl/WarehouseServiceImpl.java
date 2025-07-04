package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Inventory;
import poly.quanlyquanao.model.Warehouse;
import poly.quanlyquanao.repository.InventoryRepository;
import poly.quanlyquanao.repository.WarehouseRepository;
import poly.quanlyquanao.service.WarehouseService;

import java.time.LocalDate;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Warehouse> getAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public List<Warehouse> getAllActive() {
        return warehouseRepository.findByStatus(1);
    }

    @Override
    public Warehouse getById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    @Override
    public Warehouse create(Warehouse warehouse) {
        warehouse.setStatus(1);
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse update(Long id, Warehouse updated) {
        Warehouse exist = warehouseRepository.findById(id).orElse(null);
        if (exist != null) {
            exist.setWarehouseName(updated.getWarehouseName());
            exist.setAddress(updated.getAddress());
            return warehouseRepository.save(exist);
        }
        return null;
    }

    @Override
    public void softDelete(Long id) {
        Warehouse exist = warehouseRepository.findById(id).orElse(null);
        if (exist != null) {
            exist.setStatus(0);
            warehouseRepository.save(exist);
        }
    }

    @Override
    public List<Inventory> getInventoryByWarehouse(Long warehouseId) {
        return inventoryRepository.findByWarehouseIdAndQuantityGreaterThan(warehouseId, 0);
    }

    @Override
    public List<Inventory> getOldStockProducts(Long warehouseId, int days) {
        LocalDate thresholdDate = LocalDate.now().minusDays(days);
        return inventoryRepository.findOldStockProducts(warehouseId, thresholdDate);
    }
}