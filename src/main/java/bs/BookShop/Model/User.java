package bs.BookShop.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Userr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    public User(){}

    public User(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

}
