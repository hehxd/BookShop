package bs.BookShop.Service.Impl;

import bs.BookShop.Model.Category;
import bs.BookShop.Model.dto.CategoryDto;
import bs.BookShop.Repository.CategoryRepository;
import bs.BookShop.Service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Category> create(CategoryDto categoryDto) {
        Category category = new Category(categoryDto.getName());
        return Optional.of(this.categoryRepository.save(category));
    }

    public Optional<Category> update(Long id, CategoryDto categoryDto) {
        Category category = this.categoryRepository.findByCategoryId(id);
        category.setName(categoryDto.getName());
        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public Category delete(Long id) {
        Category category = this.categoryRepository.findByCategoryId(id);
        this.categoryRepository.deleteById(id);
        return category;
    }
}
