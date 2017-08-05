package org.etan.portal.integration.nexusinfrastructureentityapiimpl.service.impl;

import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.infrastructureentityapi.service.InfrastructureEntity;
import org.etan.portal.integration.infrastructureentityapi.service.exception.InfrastructureEntityException;
import org.osgi.service.component.annotations.Component;


@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = InfrastructureEntity.class
)
public class NexusInfrastructureEntityImpl implements InfrastructureEntity {
    @Override
    public String createInfrastructureEntityProject(String projectName) throws InfrastructureEntityException {
        return null;
    }

    @Override
    public void assignUser(User user, String infrastructureEntityProjectId) throws InfrastructureEntityException {

    }

    @Override
    public void unassignUser(User user, String infrastructureEntityProjectId) throws InfrastructureEntityException {

    }

    @Override
    public String getName() {
        return null;
    }

    // TODO: implement methods


}