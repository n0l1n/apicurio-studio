/*
 * Copyright 2017 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.hub.api.rest.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.apicurio.hub.api.Version;

/**
 * @author eric.wittmann@gmail.com
 */
@ApplicationPath("/")
@ApplicationScoped
public class HubApplication extends Application {

    private static Logger logger = LoggerFactory.getLogger(HubApplication.class);
    
    @Inject
    private Version version;

    @PostConstruct
    public void postConstruct() {
        logger.info("------------------------------------------------");
        logger.info("Starting up Apicurio Hub API");
        logger.info("\tVersion:  " + version.getVersionString());
        logger.info("\tBuilt On: " + version.getVersionDate().toString());
        logger.info("\tBuild:    " + version.getVersionInfo());
        logger.info("------------------------------------------------");
    }

}