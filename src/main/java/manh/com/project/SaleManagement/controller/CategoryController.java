package manh.com.project.SaleManagement.controller;

import manh.com.project.SaleManagement.models.Category;
import manh.com.project.SaleManagement.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    
    @PostMapping("/category/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        if(categoryService.save(category)==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(category);
    }
    @GetMapping("/category/getAll")
    public List<Category> getAllCategory(){
        return categoryService.findAll();
    }

    @PutMapping("/category/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        if(categoryService.update(category)==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("category/{id}")
    public Category findCategory(@PathVariable("id") int id){
        if(categoryService.findById(id)==null){
            return new Category();
        }
        return categoryService.findById(id);
    }

    @DeleteMapping("category/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id){
        if(categoryService.deleteById(id)==1){
            return ResponseEntity.ok().body(categoryService.findById(id));
        }
        return ResponseEntity.badRequest().build();
    }
}
