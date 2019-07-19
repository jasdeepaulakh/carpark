package carpark.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class Floor {
    int carsOnTheMove;  //not implemented yet, just an idea.
    int floorNumber;
    private List<Space> spaces;

}
