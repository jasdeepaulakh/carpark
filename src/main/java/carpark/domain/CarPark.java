package carpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class CarPark {
    private int carParkId;
    private String location;
    private String name;
    private List<Floor> floors;
}
