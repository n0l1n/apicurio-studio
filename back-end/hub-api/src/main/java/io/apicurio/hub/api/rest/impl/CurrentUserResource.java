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

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.apicurio.hub.api.exceptions.ServerError;
import io.apicurio.hub.api.github.GitHubException;
import io.apicurio.hub.api.github.IGitHubService;
import io.apicurio.hub.api.rest.ICurrentUserResource;
import io.apicurio.hub.api.security.ISecurityContext;
import io.apicurio.studio.shared.beans.User;

/**
 * @author eric.wittmann@gmail.com
 */
@ApplicationScoped
public class CurrentUserResource implements ICurrentUserResource {
    
    @Inject
    private ISecurityContext security;
    @Inject
    private IGitHubService github;

    /**
     * @see io.apicurio.hub.api.rest.ICurrentUserResource#getCurrentUser()
     */
    @Override
    public User getCurrentUser() {
        return security.getCurrentUser();
    }
    
    /**
     * @see io.apicurio.hub.api.rest.ICurrentUserResource#getOrganizations()
     */
    @Override
    public Collection<String> getOrganizations() throws ServerError {
        try {
			return this.github.getOrganizations();
		} catch (GitHubException e) {
			throw new ServerError(e);
		}
    }
    
    /**
     * @see io.apicurio.hub.api.rest.ICurrentUserResource#getRepositories(java.lang.String)
     */
    @Override
    public Collection<String> getRepositories(String org) throws ServerError {
        try {
            return this.github.getRepositories(org);
		} catch (GitHubException e) {
			throw new ServerError(e);
		}
    }

}
