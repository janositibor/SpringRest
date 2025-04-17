package TZJanosi.SpringMovie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieDto {
    private long id;
    private String title;
    private int length;
    private List<Integer> ratings=new ArrayList<>();
    private double averageRating;
}
