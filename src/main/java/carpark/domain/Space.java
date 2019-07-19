package carpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Space {
    int number;
    Type type;
    Status status;
}
