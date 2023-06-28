
package movie.database.dto.mapper;

import movie.database.dto.PersonDTO;
import movie.database.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonEntity toEntity(PersonDTO source);

    PersonDTO toDTO(PersonEntity source);

}