
package movie.database.entity.filter;

import lombok.Data;

@Data
public class MovieFilter {

    private Integer directorID;
    private Integer actorID;
    private String genre;
    private Integer fromYear;
    private Integer toYear;
    private Integer limit = 10;

}
