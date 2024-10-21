package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Category;

import java.util.List;

public interface CategoryService {
    public Category save(Category category);
    public Category findById(int id);
    public List<Category> findAll();
    public int deleteById(int id);
    public Category update(Category category);

}
