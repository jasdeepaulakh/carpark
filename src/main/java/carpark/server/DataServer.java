package carpark.server;



import carpark.domain.*;

import carpark.storage.InMemory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataServer {

    private InMemory storage = new InMemory();


    public CarPark getCarPark(int carPark) {
        return storage.getCarPark(carPark);
    }

    public Floor getCarParkFloor(int carPark, int floor){
        return storage.getCarPark(carPark).getFloors().get(floor);
    }


    public void initCarPark() {
        List<Space> spaces1 = Arrays.asList(
                new Space(1, Type.DISABLED, Status.FREE),
                new Space(2, Type.DISABLED, Status.FREE),
                new Space(3, Type.DISABLED, Status.FREE),
                new Space(4, Type.DISABLED, Status.FREE),
                new Space(5, Type.DISABLED, Status.FREE),
                new Space(6, Type.GENERAL, Status.FREE),
                new Space(7, Type.GENERAL, Status.FREE),
                new Space(8, Type.GENERAL, Status.FREE),
                new Space(9, Type.GENERAL, Status.FREE),
                new Space(10, Type.GENERAL, Status.FREE)
                );

        List<Space> spaces2 = Arrays.asList(
                new Space(1, Type.GENERAL, Status.FREE),
                new Space(2, Type.GENERAL, Status.FREE),
                new Space(3, Type.GENERAL, Status.FREE),
                new Space(4, Type.GENERAL, Status.FREE),
                new Space(5, Type.GENERAL, Status.FREE)
        );

        List<Space> spaces3 = Arrays.asList(
                new Space(1, Type.GENERAL, Status.FREE),
                new Space(2, Type.GENERAL, Status.FREE),
                new Space(3, Type.GENERAL, Status.FREE),
                new Space(4, Type.GENERAL, Status.FREE),
                new Space(5, Type.GENERAL, Status.FREE)
        );

        List<Floor> floors = Arrays.asList(
                new Floor(0,0,spaces1),
                new Floor(0,1,spaces2),
                new Floor(0,2,spaces3));

        CarPark carPark1 = new CarPark(1, "Osterley", "MSCP1", floors);
        CarPark carPark2 = new CarPark(2, "Osterley", "MSCP2", floors);
        CarPark carPark3 = new CarPark(3, "Osterley", "Sky 1 Car Park", floors);

        storage.addCarPark(1, carPark1);
        storage.addCarPark(2, carPark2);
        storage.addCarPark(3, carPark3);

    }

    public boolean parkInSpace(int carParkId, int floor, int space) {
        CarPark carPark = getCarPark(carParkId);
        Floor f = carPark.getFloors().get(floor);  //zero vbase index, user passes in 1 which is interpreted as 0
        Space s = f.getSpaces().get(space - 1);

        if (canParkInThisSpace(s)) {
            if (f.getCarsOnTheMove() > 0) {
                f.setCarsOnTheMove(f.getCarsOnTheMove() - 1);
            }
            s.setStatus(Status.OCCUPIED);
            return true;
        }

        return  false;
    }

    private boolean canParkInThisSpace(Space s) {
        return  (s.getStatus() != Status.OCCUPIED);
    }

    public Long getFreeSpacesOnFloor(int carParkId, int floor) {
        CarPark carPark = getCarPark(carParkId);
        Floor f = carPark.getFloors().get(floor);

        return f.getSpaces().stream().filter(s -> s.getStatus() == Status.FREE).count();

    }

    public Long getFreeSpaces(int carParkId) {
        CarPark carPark = getCarPark(carParkId);
        AtomicLong freeSpaceCounter = new AtomicLong();

        carPark.getFloors().stream().forEach(floor -> {
            long floorcount = floor.getSpaces().stream().filter(s -> s.getStatus() == Status.FREE).count();
            freeSpaceCounter.addAndGet(floorcount);
        });

        return freeSpaceCounter.get();
    }

    public Long getFreeSpaces(int carParkId, String type) {
        CarPark carPark = getCarPark(carParkId);
        AtomicLong freeSpaceCounter = new AtomicLong();

        carPark.getFloors().stream().forEach(floor -> {
            long floorcount = floor.getSpaces().stream().filter(s -> s.getStatus() == Status.FREE && s.getType().toString().equalsIgnoreCase(type)).count();
            freeSpaceCounter.addAndGet(floorcount);
        });

        return freeSpaceCounter.get();
    }

    public Long getFreeSpacesOnFloor(int carParkId, int floor, String type) {
        CarPark carPark = getCarPark(carParkId);
        Floor f = carPark.getFloors().get(floor);

        return f.getSpaces().stream().filter(s -> s.getStatus() == Status.FREE && s.getType().toString().equalsIgnoreCase(type)).count();

    }
}
