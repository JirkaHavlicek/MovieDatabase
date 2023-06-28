
package movie.database.service;

import movie.database.dto.MovieDTO;
import movie.database.dto.mapper.MovieMapper;
import movie.database.entity.MovieEntity;
import movie.database.entity.PersonEntity;
import movie.database.entity.filter.MovieFilter;
import movie.database.entity.repository.MovieRepository;
import movie.database.entity.repository.PersonRepository;
import movie.database.entity.repository.specification.MovieSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieMapper movieMapper;

    public MovieDTO addMovie(MovieDTO movieDTO){
        MovieEntity movie = movieMapper.toEntity(movieDTO);
        mapPeopleToMovie(movie, movieDTO);
        MovieEntity saved = movieRepository.save(movie);
        return movieMapper.toDTO(saved);
    }

    public List<MovieDTO> getAllMovies(MovieFilter movieFilter){
        MovieSpecification movieSpecification = new MovieSpecification(movieFilter);

        return movieRepository.findAll(movieSpecification, PageRequest.of(0, movieFilter.getLimit()))
                .stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO editMovie(MovieDTO movieDTO, long id){
        movieDTO.setId(id);
        MovieEntity entity = movieRepository.getReferenceById(id);
        movieMapper.updateEntity(movieDTO, entity);

        mapPeopleToMovie(entity, movieDTO);
        MovieEntity saved = movieRepository.save(entity);
        return movieMapper.toDTO(saved);
    }

    private void mapPeopleToMovie(MovieEntity movie, MovieDTO movieDTO){
        movie.setActors(new ArrayList<>());

        List<PersonEntity> people = personRepository.findAllById(movieDTO.getActorIDs());
        movie.getActors().addAll(people);
        movie.setDirector(personRepository.getReferenceById(movieDTO.getDirectorID()));
    }

    public MovieDTO removeMovie(Long id){
        MovieEntity movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        MovieDTO model = movieMapper.toDTO(movie);

        movieRepository.delete(movie);
        return model;
    }

    public MovieDTO getMovie(Long id) {
        MovieEntity movie = movieRepository.getReferenceById(id);
        return movieMapper.toDTO(movie);
    }
}
