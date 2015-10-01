package org.pachnanada.services;

import io.dropwizard.setup.Environment;
import org.pachnanada.server.FantasyFootballConfiguration;

/**
 * Created by gautampachnanda on 01/10/15.
 */
public interface ServicesFactory {

    FantasyFootballServices build(Environment environment, FantasyFootballConfiguration configuration);
}
