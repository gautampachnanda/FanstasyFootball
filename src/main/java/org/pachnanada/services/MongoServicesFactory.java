package org.pachnanada.services;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.MongoClient;
import io.dropwizard.lifecycle.Managed;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.pachnanada.dao.TeamDao;
import org.pachnanada.entities.Team;
import org.pachnanada.server.FantasyFootballConfiguration;
import org.pachnanada.server.dropwizard.health.MongoConnectionHealth;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MongoServicesFactory implements ServicesFactory {
    @NotEmpty
    private String host = "localhost";

    @Min(1)
    @Max(65535)
    @JsonProperty
    private int port = 27017;

    @JsonProperty
    @NotEmpty
    private String db = "meta";

    @JsonProperty
    @NotEmpty
    private String dbCollection = "counters";

    public MongoServicesFactory() {
    }

    @JsonProperty
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @JsonProperty
    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    @JsonProperty
    public String getDbCollection() {
        return dbCollection;
    }

    public void setDbCollection(String dbCollection) {
        this.dbCollection = dbCollection;
    }

    @Override
    public FantasyFootballServices build(Environment environment, FantasyFootballConfiguration configuration) {
        try {
            MongoClient client = new MongoClient(getHost(), getPort());
            environment.lifecycle().manage(new Managed() { // TODO create a class instead of anonymous
                @Override
                public void start() {
                }

                @Override
                public void stop() {
                    client.close();
                }
            });
            environment.healthChecks().register("mongo", new MongoConnectionHealth(client));
            return new MongoCountersServices(client, getDb());
        } catch (UnknownHostException e) {
            throw new RuntimeException("Problem building CountersServices", e);
        }
    }

    private final static class MongoCountersServices implements FantasyFootballServices {


        private final TeamDao teamDao;

        public MongoCountersServices(MongoClient client, String db) {

            Datastore datastore = new Morphia().createDatastore(client, db);
            this.teamDao = new TeamDao(datastore);
        }

        @Override
        public List<String> teamNames() {
            List<String> teamNames = new ArrayList<String>();
            for (Team team : teamDao.find()) {
                teamNames.add(team.getName());
            }
            return teamNames;
        }

        @Override
        public void addTeam(Team team) {
            this.teamDao.save(team);
        }

        @Override
        public void addTeams(List<Team> teams) {
            for (Team team : teams) {
                addTeam(team);
            }
        }
    }
}