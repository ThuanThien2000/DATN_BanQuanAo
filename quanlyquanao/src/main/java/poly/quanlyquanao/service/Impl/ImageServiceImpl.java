package poly.quanlyquanao.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.quanlyquanao.model.Image;
import poly.quanlyquanao.model.Product;
import poly.quanlyquanao.repository.ImageRepository;
import poly.quanlyquanao.repository.ProductRepository;
import poly.quanlyquanao.service.ImageService;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Image> getAllByProductId(Long productId) {
        return imageRepository.findByProductIdAndStatus(productId, 1); // chỉ lấy ảnh còn hoạt động
    }

    @Override
    public Image addImage(Long productId, Image image) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return null;

        image.setProduct(product);
        image.setStatus(1); // mặc định là còn hoạt động
        return imageRepository.save(image);
    }

    @Override
    public boolean softDelete(Long id) {
        Image img = imageRepository.findById(id).orElse(null);
        if (img == null) return false;

        img.setStatus(0); // soft delete
        imageRepository.save(img);
        return true;
    }
}
