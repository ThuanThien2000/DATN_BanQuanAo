package poly.quanlyquanao.service;

import poly.quanlyquanao.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> getAllByProductId(Long productId);
    Image addImage(Long productId, Image image);
    boolean softDelete(Long id);
}
