package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.models.Product;
import manh.com.project.SaleManagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Clock;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<Product> addProduct(@RequestParam("product_id") int id,@RequestParam("product_name") String name
            ,@RequestParam("quantity") int quantity,@RequestParam("price") double price
            ,@RequestParam("category_id") int categoryId,@RequestParam("product_image") MultipartFile file) throws IOException {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setCategoryId(categoryId);
         if(productService.save(product,file)==null){
             return ResponseEntity.badRequest().build();
         }
         productService.uploadImage(file);
         return ResponseEntity.ok(product);
    }

    @GetMapping("/product/findAll")
    public List<Product> getAllProducts() {
       return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        Product product = productService.findById(id);
        if(product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        if(!productService.delete(id)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("product/update")
    public ResponseEntity<Product> updateProduct(@RequestParam(value = "product_id") int id,@RequestParam("product_name") String name
            ,@RequestParam("quantity") int quantity,@RequestParam("price") double price
            ,@RequestParam("category_id") int categoryId,@RequestParam("product_image") MultipartFile file) throws IOException {
       Product product = new Product();
       product.setId(id);
       product.setName(name);
       product.setQuantity(quantity);
       product.setPrice(price);
       product.setCategoryId(categoryId);
        System.out.println(productService.findById(id).toString());
      if(Objects.equals(file.getOriginalFilename(), "")){
          product.setImage(productService.findById(id).getImage());
      }
      else {
          product.setImage(file.getOriginalFilename());
      }

      if(productService.update(product)==null){
          return ResponseEntity.badRequest().build();
      }
        if(!Objects.equals(file.getOriginalFilename(), "")){
          productService.uploadImage(file);
      }
      return ResponseEntity.ok(product);
    }

    @GetMapping("product/loadByCategoryId/{id}")
    public List<Product> loadProductByCategoryId(@PathVariable("id") int id){
        return productService.findProductByCategory(id);
    }

    @GetMapping("/product/findProduct/{id}")
    public Product findProductById(@PathVariable("id") int id){
        return productService.findProductById(id);
    }
}
