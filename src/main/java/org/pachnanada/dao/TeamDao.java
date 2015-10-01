package org.pachnanada.dao;


import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.pachnanada.entities.Team;

/**
 * Created by gautampachnanda on 01/10/15.
 */
public class TeamDao extends BasicDAO<Team, ObjectId> {


    public TeamDao(Datastore ds) {
        super(Team.class, ds);
    }
}
