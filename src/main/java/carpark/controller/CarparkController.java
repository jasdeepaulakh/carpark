package carpark.controller;

import carpark.domain.CarPark;
import carpark.domain.Floor;
import carpark.server.DataServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class CarparkController {

    @Autowired
    private DataServer dataServer;

    //Get the status of the whole car park. (Just call http://localhost:8080)
    @RequestMapping(value="/{carParkId}", method = GET)
    public ResponseEntity<CarPark> getCarPark(@PathVariable int carParkId) {
        return new ResponseEntity<>(dataServer.getCarPark(carParkId), HttpStatus.OK);

    }

    //POST method to reserve a space
    @RequestMapping(value = "/park/{carParkId}/{floor}/{space}", method=POST)
    public ResponseEntity<Floor> park(@PathVariable int carParkId, @PathVariable int floor, @PathVariable int space){
        boolean canPark = dataServer.parkInSpace(carParkId, floor, space);

        if (canPark) {
            return new ResponseEntity<>(dataServer.getCarParkFloor(carParkId, floor), HttpStatus.OK);

        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, String.format("Space %d on floor %d Occupied/Resevered cannot park here", floor, space));
        }
    }

    //@TODO
    //Need a method to free up space.


    @RequestMapping(value="/freespaces/{carParkId}")
    public ResponseEntity<Long> getFreeSpaces(@PathVariable int carParkId) {

        return new ResponseEntity<>(dataServer.getFreeSpaces(carParkId), HttpStatus.OK);
    }



    @RequestMapping(value="/freespaces/{carParkId}/{floor}")
    public ResponseEntity<Long> getFreeSpacesOnFloor(@PathVariable int carParkId, @PathVariable int floor) {

        return new ResponseEntity<>(dataServer.getFreeSpacesOnFloor(carParkId, floor), HttpStatus.OK);
    }

    //Get spaces by floor
    @RequestMapping(value="/{carParkId}/floor/{floor}", method = GET)
    public ResponseEntity<Floor> getCarParkByLevel(@PathVariable int carParkId, @PathVariable int floor) {
        return new ResponseEntity<>(dataServer.getCarParkFloor(carParkId, floor), HttpStatus.OK);
    }

    //@TODO should we have some car park stats. i.e. Floor=1, Total=5, Free=3, OnTheMove=2
    //so we can track cars on the move, and provide some indication of how likely it is
    //to get a space

    @RequestMapping(value="/freespacesbytype/{carParkId}/{type}")
    public ResponseEntity<Long> getFreeSpaces(@PathVariable int carParkId, @PathVariable String type) {


        return new ResponseEntity<>(dataServer.getFreeSpaces(carParkId, type), HttpStatus.OK);
    }

    @RequestMapping(value="/freespacesbytype/{carParkId}/{floor}/{type}")
    public ResponseEntity<Long> getFreeSpacesOnFloor(@PathVariable int carParkId, @PathVariable int floor, @PathVariable String type) {

        return new ResponseEntity<>(dataServer.getFreeSpacesOnFloor(carParkId, floor, type), HttpStatus.OK);
    }


}
