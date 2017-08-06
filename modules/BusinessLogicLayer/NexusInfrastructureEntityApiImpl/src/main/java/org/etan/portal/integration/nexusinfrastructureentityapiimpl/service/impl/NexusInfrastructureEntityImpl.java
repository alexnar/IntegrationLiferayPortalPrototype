package org.etan.portal.integration.nexusinfrastructureentityapiimpl.service.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.User;
import org.etan.portal.integration.infrastructureentityapi.service.InfrastructureEntity;
import org.etan.portal.integration.infrastructureentityapi.service.exception.InfrastructureEntityException;
import org.etan.portal.integration.nexusservice.service.NexusService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;


@Component(
        immediate = true,
        property = {
                // TODO enter required service properties
        },
        service = InfrastructureEntity.class
)
public class NexusInfrastructureEntityImpl implements InfrastructureEntity {

    private static final String USER_NEXUS_ID_FIELD = "nexusUserId";
    private static final String NEXUS_REPOSITORY_NAME = "NEXUS REPOSITORY";

    @Reference
    private NexusService nexusService;

    @Override
    public String createInfrastructureEntityProject(String projectName) throws InfrastructureEntityException {
        String repositoryId = nexusService.createRepository(projectName);
        return repositoryId;
    }

    @Override
    public void assignUser(User user, String infrastructureEntityProjectId) throws InfrastructureEntityException {
        // TODO: Check if it works.
        ExpandoBridge userExpandoBridge = user.getExpandoBridge();
        Serializable userNexusIdSerializable = userExpandoBridge.getAttribute(USER_NEXUS_ID_FIELD);
        String userNexusId = (String) userNexusIdSerializable;
        nexusService.assignUserToRepository(userNexusId, infrastructureEntityProjectId);
    }

    @Override
    public void unassignUser(User user, String infrastructureEntityProjectId) throws InfrastructureEntityException {
        // TODO: Check if it works.
        ExpandoBridge userExpandoBridge = user.getExpandoBridge();
        Serializable userNexusIdSerializable = userExpandoBridge.getAttribute(USER_NEXUS_ID_FIELD);
        String userNexusId = (String) userNexusIdSerializable;
        nexusService.unassignUserFromRepository(userNexusId, infrastructureEntityProjectId);
    }

    @Override
    public String getName() {
        return NEXUS_REPOSITORY_NAME;
    }

    // TODO: implement methods


}