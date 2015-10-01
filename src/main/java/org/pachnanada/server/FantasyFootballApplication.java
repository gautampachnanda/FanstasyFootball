package org.pachnanada.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.pachnanada.entities.Team;
import org.pachnanada.server.resources.FantasyFootballResource;
import org.pachnanada.services.FantasyFootballServices;
import org.pachnanada.services.ServicesFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gautampachnanda on 01/10/15.
 */
public class FantasyFootballApplication extends Application<FantasyFootballConfiguration> {

    public FantasyFootballApplication() {
    }

    public static void main(String[] args) throws Exception {
        new FantasyFootballApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<FantasyFootballConfiguration> bootstrap) {
    }

    @Override
    public void run(FantasyFootballConfiguration configuration, Environment environment) {

        ServicesFactory servicesFactory = configuration.getServicesFactory();
        FantasyFootballServices services = servicesFactory.build(environment, configuration);
        FantasyFootballResource resource = new FantasyFootballResource(services);


        services.addTeams(initTeams());
        environment.jersey().register(resource);
    }

    private List<Team> initTeams() {
        List<Team> teams = new ArrayList<>();

        for (String teamName : Arrays.asList("ManU", "ManCity", "Chelsea", "Arsenal")) {
            Team team = new Team(teamName);
            teams.add(team);
        }
        return teams;
    }
}