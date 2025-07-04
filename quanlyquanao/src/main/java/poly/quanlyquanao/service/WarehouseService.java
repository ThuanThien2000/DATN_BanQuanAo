package poly.quanlyquanao.service;

import poly.quanlyquanao.model.Warehouse;
import poly.quanlyquanao.model.Inventory;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> getAll();
    List<Warehouse> getAllActive();
    Warehouse getById(Long id);
    Warehouse create(Warehouse warehouse);
    Warehouse update(Long id, Warehouse warehouse);
    void softDelete(Long id);

    List<Inventory> getInventoryByWarehouse(Long warehouseId);
    List<Inventory> getOldStockProducts(Long warehouseId, int days);
}