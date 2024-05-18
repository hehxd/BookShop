package bs.BookShop.Model.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CategoryDto {

    private String name;

    public CategoryDto(String name) {
        this.name = name;
    }
}
