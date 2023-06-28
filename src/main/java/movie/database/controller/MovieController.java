

package movie.database.controller;

import movie.database.configuration.GenreConfiguration;
import movie.database.dto.MovieDTO;
import movie.database.entity.filter.MovieFilter;
import movie.database.service.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    public MovieServiceImpl movieService;

    @Autowired
    public GenreConfiguration genreConfiguration;

    @GetMapping({"/genres", "/genres/"})
    public List<String> getGenres(){
        return genreConfiguration.getGenres();
    }

    @Secured("ROLE_ADMIN")
    @PostMapping({"/movies", "/movies/"})
    public MovieDTO addMovie(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }

    @GetMapping({"/movies", "/movies/"})
    public List<MovieDTO> getAllMovies(MovieFilter movieFilter){
        return movieService.getAllMovies(movieFilter);
    }

    @GetMapping({"/movies/{id}", "/movies/{id}/"})
    public MovieDTO getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping({"/movies/{id}", "/mvoies/{id}/"})
    public MovieDTO editMovie(@RequestBody MovieDTO movieDTO, @PathVariable long id){
        return movieService.editMovie(movieDTO, id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping({"/movies/{id}", "/movies/{id}/"})
    public void removeMovie(@PathVariable Long id){
        movieService.removeMovie(id);
    }
}
