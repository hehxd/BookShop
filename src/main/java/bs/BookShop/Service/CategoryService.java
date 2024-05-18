package bs.BookShop.Service;

import bs.BookShop.Model.Category;
import bs.BookShop.Model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> listCategories();
    Category findById(Long id);
    Optional<Category> create(CategoryDto categoryDto);
    Optional<Category> update(Long id, CategoryDto categoryDto);
    Category delete(Long id);
}
