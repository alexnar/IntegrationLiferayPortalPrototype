package org.etan.portal.integration.prototype.nexusinfrastructureentityapiimpl.service.impl;

import org.etan.portal.integration.prototype.infrastructureentityapi.service.InfrastructureEntityApi;

import org.osgi.service.component.annotations.Component;


@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = InfrastructureEntityApi.class
)
public class NexusInfrastructureEntityApiImpl implements InfrastructureEntityApi {

    // TODO: implement methods

    @Override
    public String createInfrastructureEntityProject(String projectName) {
        return null;
    }

    @Override
    public void assignUser(long userId, String infrastructureEntityProjectId) {

    }

    @Override
    public void unassignUser(long userId, String infrastructureEntityProjectId) {

    }

    @Override
    public String getName() {
        return null;
    }

}