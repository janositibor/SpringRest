package TZJanosi.SpringMovie.service;

import TZJanosi.SpringMovie.dto.CreateMovieCommand;
import TZJanosi.SpringMovie.dto.MovieDto;
import TZJanosi.SpringMovie.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MovieService {
    private AtomicLong atomicLong=new AtomicLong();
    private List<Movie> movies=new ArrayList<>();
    private ModelMapper modelMapper;

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieDto createAndAddMovie(CreateMovieCommand command){
        Movie movie=modelMapper.map(command,Movie.class);
        long id=atomicLong.getAndIncrement();
        movie.setId(id);
        addMovie(movie);
        return modelMapper.map(movie,MovieDto.class);
    }
    public void addMovie(Movie movie){
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    private Movie findMovieById(long id){
        return movies.stream().filter(m->m.getId()==id).findFirst().orElseThrow(() -> new IllegalArgumentException("No movie with id: "+id));
    }

    public MovieDto getMovieDtoById(long id) {
        Movie movie=findMovieById(id);
        return modelMapper.map(movie,MovieDto.class);
    }

    public List<MovieDto> findAllMovies() {
        return movies.stream().map(m->modelMapper.map(m,MovieDto.class)).toList();
    }



    public List<Integer> addRatingForMovie(long id, int rating) {
        Movie movie=findMovieById(id);
        movie.addRating(rating);
        return new ArrayList<>(movie.getRatings());
    }

    public List<Integer> findRatingsForMovie(long id) {
//        return movies.stream().filter(m->m.getId()==id).map(m->m.getRatings()).findFirst().orElseThrow(()->new IllegalArgumentException("No movie with id: "+id));
        Movie movie=findMovieById(id);
        return new ArrayList<>(movie.getRatings());
    }
}
