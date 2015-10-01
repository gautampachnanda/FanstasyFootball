package org.pachnanada.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.pachnanada.services.MongoServicesFactory;

/**
 * Created by gautampachnanda on 01/10/15.
 */
public class FantasyFootballConfiguration extends Configuration {
    @JsonProperty("mongo")
    private MongoServicesFactory servicesFactory = new MongoServicesFactory();


    public FantasyFootballConfiguration() {
    }

    public MongoServicesFactory getServicesFactory() {
        return servicesFactory;
    }

    public void setServicesFactory(MongoServicesFactory servicesFactory) {
        this.servicesFactory = servicesFactory;
    }

    public void setMongoFactory(MongoServicesFactory mongoFactory) {
        this.servicesFactory = mongoFactory;
    }

}