package carpark.storage;


import carpark.domain.CarPark;
import java.util.HashMap;
import java.util.Map;

public class InMemory {

    Map<Integer, CarPark> storedCarPark = new HashMap<>();

    public void addCarPark(int id, CarPark carPark) {
        storedCarPark.put(id, carPark);
    }

    public CarPark getCarPark(int id) {
        return storedCarPark.get(id);
    }

}
