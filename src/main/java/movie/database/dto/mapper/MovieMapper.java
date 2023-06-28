/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */

package movie.database.dto.mapper;

import movie.database.dto.MovieDTO;
import movie.database.entity.MovieEntity;
import movie.database.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "dateAdded", ignore = true)
    @Mapping(target = "actors", ignore = true)
    @Mapping(target = "director", ignore = true)
    MovieEntity toEntity(MovieDTO source);

    @Mapping(target = "dateAdded", ignore = true)
    @Mapping(target = "actors", ignore = true)
    @Mapping(target = "director", ignore = true)
    void updateEntity(MovieDTO source, @MappingTarget MovieEntity target);

    @Mapping(target = "directorID", source = "director.id")
    @Mapping(target = "actorIDs", expression = "java(getActorIds(source))")
    MovieDTO toDTO(MovieEntity source);

    default List<Long> getActorIds(MovieEntity source) {
        List<Long> result = new ArrayList<>();
        for (PersonEntity person : source.getActors()) {
            result.add(person.getId());
        }
        return result;
    }

}
