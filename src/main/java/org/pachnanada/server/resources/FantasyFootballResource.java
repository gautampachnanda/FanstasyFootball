package org.pachnanada.server.resources;

import com.codahale.metrics.annotation.Timed;
import org.pachnanada.services.FantasyFootballServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by gautampachnanda on 01/10/15.
 */
@Path("/")
public class FantasyFootballResource {


    private final FantasyFootballServices services;

    public FantasyFootballResource(FantasyFootballServices services) {
        this.services=services;
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> list() {
        return services.teamNames();
    }

}
