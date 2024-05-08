package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Book;
import bs.BookShop.Model.Category;
import bs.BookShop.Repository.CategoryRepository;
import bs.BookShop.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findByCategoryId(id);
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, String name) {
        Category category = this.categoryRepository.findByCategoryId(id);
        category.setName(name);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category delete(Long id) {
        Category category = this.categoryRepository.findByCategoryId(id);
        this.categoryRepository.deleteById(id);
        return category;
    }
}
