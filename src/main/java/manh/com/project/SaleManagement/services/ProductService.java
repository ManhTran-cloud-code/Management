package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product, MultipartFile file);
    boolean delete(int id);
    Product update(Product product);
    void uploadImage(MultipartFile file) throws IOException;
    List<Product> findProductByCategory(int categoryId);
    Product findProductById(int id);
    boolean updateQuantityProduct(int quantity, int productId);
}
