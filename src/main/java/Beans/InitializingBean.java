package Beans;

import carpark.server.DataServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializingBean {

    @Autowired
    DataServer dataServer;

    /**
     * Creates the whole car park when this Bean get initialized.
     */
    @PostConstruct
    public void init() {
        dataServer.initCarPark();
    }

}

