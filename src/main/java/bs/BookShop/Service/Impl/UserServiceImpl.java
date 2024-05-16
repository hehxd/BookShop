package bs.BookShop.Service.Impl;

import bs.BookShop.Model.User;
import bs.BookShop.Model.exceptions.UserNotFoundException;
import bs.BookShop.Repository.UserRepository;
import bs.BookShop.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User create(String firstName, String lastName, String address) {
        User user = new User(firstName, lastName, address);
        return this.userRepository.save(user);
    }

    @Override
    public User update(Long userId, String firstName, String lastName, String address) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        return this.userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        this.userRepository.deleteById(id);
        return user;
    }
}
