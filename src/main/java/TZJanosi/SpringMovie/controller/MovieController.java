package TZJanosi.SpringMovie.controller;

import TZJanosi.SpringMovie.dto.CreateMovieCommand;
import TZJanosi.SpringMovie.dto.MovieDto;
import TZJanosi.SpringMovie.service.MovieService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {
    private MovieService service;
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public MovieDto findMovieById(@PathVariable("id") long id){
        return service.getMovieDtoById(id);
    }
    @GetMapping("/{id}/ratings")
    public List<Integer> findRatingsForMovie(@PathVariable("id") long id){
        return service.findRatingsForMovie(id);
    }
    @PostMapping("/{id}/ratings")
    public List<Integer> addRatingForMovie(@PathVariable("id") long id, @RequestBody int rating){
        return service.addRatingForMovie(id,rating);
    }
    @GetMapping("")
    public List<MovieDto> findAllMovies(){
        return service.findAllMovies();
    }

    @PostMapping("")
    public MovieDto addMovie(@RequestBody CreateMovieCommand command){
        return service.createAndAddMovie(command);
    }
}
