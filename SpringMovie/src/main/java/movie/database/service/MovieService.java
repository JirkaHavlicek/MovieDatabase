package movie.database.service;

import movie.database.dto.MovieDTO;
import movie.database.entity.filter.MovieFilter;

import java.util.List;

public interface MovieService {

    MovieDTO addMovie(MovieDTO movieDTO);

    List<MovieDTO> getAllMovies(MovieFilter movieFilter);

    MovieDTO editMovie(MovieDTO movieDTO, long id);

    MovieDTO removeMovie(Long id);

    MovieDTO getMovie(Long id);

}
