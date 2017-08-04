package org.etan.portal.integration.prototype.nexusinfrastructureentityapiimpl.service.impl;

import org.etan.portal.integration.prototype.infrastructureentityapi.service.InfrastructureEntity;

import org.etan.portal.integration.prototype.infrastructureentityapi.service.exception.InfrastructureEntityException;
import org.osgi.service.component.annotations.Component;


@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = InfrastructureEntity.class
)
public class NexusInfrastructureEntityImpl implements InfrastructureEntity {

    // TODO: implement methods

    @Override
    public String createInfrastructureEntityProject(String projectName) throws InfrastructureEntityException {
        return null;
    }

    @Override
    public void assignUser(long userId, String infrastructureEntityProjectId) throws InfrastructureEntityException {

    }

    @Override
    public void unassignUser(long userId, String infrastructureEntityProjectId) throws InfrastructureEntityException {

    }

    @Override
    public String getName() {
        return null;
    }


}