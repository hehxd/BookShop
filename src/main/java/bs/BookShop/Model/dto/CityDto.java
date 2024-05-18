package bs.BookShop.Model.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CityDto {

    private String name;

    public CityDto(String name) {
        this.name = name;
    }
}
