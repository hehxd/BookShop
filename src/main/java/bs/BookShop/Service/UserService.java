package bs.BookShop.Service;

import bs.BookShop.Model.User;

import java.util.List;

public interface UserService {

    List<User> listUsers();
    User findById(Long id);
    User create(String firstName, String lastName, String address);
    User update(Long userId, String firstName, String lastName, String address);
    User delete(Long id);



}
