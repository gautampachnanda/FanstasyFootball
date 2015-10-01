package org.pachnanada.services;

import org.pachnanada.entities.Team;

import java.util.List;

/**
 * Created by gautampachnanda on 01/10/15.
 */
public interface FantasyFootballServices {
        List<String> teamNames();
        void addTeam(Team team);
        void addTeams(List<Team> teams);
}
