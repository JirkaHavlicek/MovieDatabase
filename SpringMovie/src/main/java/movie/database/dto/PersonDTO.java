
package movie.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import movie.database.constant.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty("_id")
    private long id;

    private String name;

    private Date birthDate;

    private String country;

    private String biography;

    private RoleType role;

}
