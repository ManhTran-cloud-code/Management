package manh.com.project.SaleManagement.services;

import manh.com.project.SaleManagement.models.Category;
import manh.com.project.SaleManagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(int id) {
        try{
          Category category = categoryRepository.getCategory(id);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return categoryRepository.getCategory(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.getAll();
    }

    @Override
    public int deleteById(int id) {
        return categoryRepository.delete(id);
    }

    @Override
    public Category update(Category category) {
        if(categoryRepository.update(category)==0){
            return null;
        }
        return category;
    }
}
