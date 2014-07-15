/**
 * Copyright (c) 2014 openHAB UG (haftungsbeschraenkt) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.smarthome.config.core;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * The {@link ConfigDescription} class contains a description for a concrete
 * configuration of e.g. a {@code Thing}, a {@code Bridge} or other specific
 * configurable services. This class <i>does not</i> contain the configuration
 * data itself and is usually used for data validation of the concrete
 * configuration or for supporting user interfaces.
 * <p>
 * The description is stored within the {@link ConfigDescriptionRegistry} under
 * the given URI. The URI has to follow the syntax
 * {@code 'scheme://<token>:<token>:...'}.
 * <p>
 * <b>Hint:</b> This class is immutable.
 * 
 * @author Michael Grammling - Initial Contribution
 * @author Dennis Nobel - Initial Contribution
 */
public class ConfigDescription {

    private URI uri;
    private List<ConfigDescriptionParameter> parameters;


    /**
     * Creates a new instance of this class with the specified parameter.
     * 
     * @param uri the URI of this description within the {@link ConfigDescriptionRegistry}
     * @throws IllegalArgumentException if the URI is null
     */
    public ConfigDescription(URI uri) throws IllegalArgumentException {
        this(uri, null);
    }

    /**
     * Creates a new instance of this class with the specified parameters.
     * 
     * @param uri the URI of this description within the {@link ConfigDescriptionRegistry}
     *     (must neither be null nor empty)
     * 
     * @param parameters the description of a concrete configuration parameter
     *     (could be null or empty)
     *
     * @throws IllegalArgumentException if the URI is null
     */
    public ConfigDescription(URI uri, List<ConfigDescriptionParameter> parameters) {
        if (uri == null) {
            throw new IllegalArgumentException("The URI must not be null!");
        }

        this.uri = uri;

        if (parameters != null) {
            this.parameters = Collections.unmodifiableList(parameters);
        } else {
            this.parameters = Collections.unmodifiableList(
                    new ArrayList<ConfigDescriptionParameter>(0));
        }
    }

    /**
     * Returns the URI of this description within the {@link ConfigDescriptionRegistry}.
     * The URI follows the syntax {@code 'scheme://<token>:<token>:...'}.
     *  
     * @return the URI of this description (not null)
     */
    public URI getURI() {
        return this.uri;
    }

    /**
     * Returns the description of a concrete configuration parameter.
     * <p>
     * The returned list is immutable.
     * 
     * @return the description of a concrete configuration parameter (not null, could be empty)
     */
    public List<ConfigDescriptionParameter> getParameters() {
        return this.parameters;
    }

    @Override
    public String toString() {
        return "ConfigDescription [uri=" + uri + ", parameters=" + parameters + "]";
    }

}