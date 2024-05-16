package bs.BookShop.Model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super(String.format("User with id %d does not exist.", userId));
    }
}
