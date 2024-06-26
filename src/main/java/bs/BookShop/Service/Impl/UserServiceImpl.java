package bs.BookShop.Service.Impl;

import bs.BookShop.Model.User;
import bs.BookShop.Model.dto.UserDto;
import bs.BookShop.Model.exceptions.UserNotFoundException;
import bs.BookShop.Repository.UserRepository;
import bs.BookShop.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<User> create(UserDto userDto) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getAddress());
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public Optional<User> update(Long userId, UserDto userDto) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        return Optional.of(this.userRepository.save(user));
    }

    @Override
    public User delete(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        this.userRepository.deleteById(id);
        return user;
    }
}
