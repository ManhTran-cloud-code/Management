package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Product;
import manh.com.project.SaleManagement.repositories.ProductRepository;
import manh.com.project.SaleManagement.utis.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product findById(int id) throws EmptyResultDataAccessException {
        return productRepository.getProduct(id);
    }


    @Override
    public Product save(Product product, MultipartFile file) {
        product.setImage(file.getOriginalFilename());
        if(productRepository.addProduct(product)==0){
            return null;
        }
        return product;
    }

    @Override
    public boolean delete(int id) {
        return productRepository.deleteProduct(id) != 0;
    }

    @Override
    public Product update(Product product) {
        if(productRepository.updateProduct(product)==0){
            return null;
        }
        return product;
    }

    @Override
    public void uploadImage(MultipartFile file) throws IOException{
        File saveFile = new ClassPathResource(Constants.LINK_UPLOAD_PRODUCT_IMAGE).getFile();
        Path path = Paths
                .get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public List<Product> findProductByCategory(int categoryId) {
        return productRepository.findProductByCategory(categoryId);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public boolean updateQuantityProduct(int quantity, int productId) {
        return productRepository.updateQuantityProduct(quantity,productId)>0;
    }
}
