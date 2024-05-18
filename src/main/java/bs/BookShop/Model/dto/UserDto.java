package bs.BookShop.Model.dto;


import lombok.Data;

@Data
public class UserDto {
    private String firstName;

    private String lastName;

    private String address;

    public UserDto(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
