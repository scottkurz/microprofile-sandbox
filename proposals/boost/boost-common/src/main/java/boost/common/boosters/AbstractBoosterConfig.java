/*******************************************************************************
 * Copyright (c) 2018, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package boost.common.boosters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import boost.common.BoostException;

/**
 * Interface to describe common function across all technology Booster Pack
 * Config Objects
 *
 */
public abstract class AbstractBoosterConfig {

    public static final String RUNTIMES_GROUP_ID = "boost.runtimes";
    public static final String BOOSTERS_GROUP_ID = "boost.boosters";
    public static final String CDI_VERSION_12 = "0.1.2-SNAPSHOT";
    public static final String CDI_VERSION_20 = "0.2.0-SNAPSHOT";
    public static final String JAXRS_VERSION_20 = "0.2.0-SNAPSHOT";
    public static final String JAXRS_VERSION_21 = "0.2.1-SNAPSHOT";
    public static final String JSONP_VERSION_10 = "0.1.0-SNAPSHOT";
    public static final String JSONP_VERSION_11 = "0.1.1-SNAPSHOT";
    public static final String JPA_VERSION_21 = "0.2.1-SNAPSHOT";
    public static final String JPA_VERSION_22 = "0.2.2-SNAPSHOT";
    public static final String MP_HEALTH_VERSION_10 = "0.1.0-SNAPSHOT";
    public static final String MP_CONFIG_VERSION_13 = "0.1.3-SNAPSHOT";
    public static final String MP_OPENTRACING_VERSION_11 = "0.1.1-SNAPSHOT";
    public static final String MP_OPENTRACING_VERSION_12 = "0.1.2-SNAPSHOT";
    public static final String MP_RESTCLIENT_VERSION_11 = "0.1.1-SNAPSHOT";

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface BoosterCoordinates {
        String value();
    }

    public static String getCoordinates(Class<?> klass) throws BoostException {
        BoosterCoordinates coordinates = klass.getAnnotation(BoosterCoordinates.class);
        if (coordinates == null) {
            throw new BoostException(
                    String.format("class '%s' must have a BoosterCoordinates annotation", klass.getName()));
        }
        return coordinates.value();
    }

    private final String version;

    protected AbstractBoosterConfig(String version) {
        this.version = version;
    }

    /**
     * Return the dependency that this booster requires
     * 
     * @return
     */
    public abstract List<String> getDependencies();

    public String getVersion() {
        return version;
    }

}
