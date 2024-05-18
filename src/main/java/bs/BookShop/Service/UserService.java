package bs.BookShop.Service;

import bs.BookShop.Model.User;
import bs.BookShop.Model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listUsers();
    User findById(Long id);
    Optional<User> create(UserDto userDto);
    Optional<User> update(Long userId, UserDto userDto);
    User delete(Long id);



}
