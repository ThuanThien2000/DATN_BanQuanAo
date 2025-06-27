package poly.quanlyquanao.service.Impl;

import poly.quanlyquanao.model.Warehouse;

import java.util.List;

public interface IWarehouseService {
    Warehouse getWarehouseById(Long id);
    List<Warehouse> getAllWarehouses();
    Warehouse addWarehouse(Warehouse warehouse);
    Warehouse updateWarehouse(Long id, Warehouse warehouse);
    void deleteWarehouse(Long id);
}