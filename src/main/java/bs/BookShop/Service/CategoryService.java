package bs.BookShop.Service;

import bs.BookShop.Model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
    Category findById(Long id);
    Category create(String name);
    Category update(Long id, String name);
    Category delete(Long id);
}
