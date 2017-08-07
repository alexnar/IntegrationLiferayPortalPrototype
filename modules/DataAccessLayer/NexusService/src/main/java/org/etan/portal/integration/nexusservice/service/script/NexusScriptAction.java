package org.etan.portal.integration.nexusservice.service.script;

/**
 * Actions enum, contain all actions
 * that can be done with nexus repository
 * manager.
 */
public enum NexusScriptAction {
    CREATE_MAVEN_HOSTED("createMavenHosted"),
    ASSIGN_USER("assignUser"),
    UNASSIGN_USER("unassignUser"),
    LAST_ARTIFACTS("lastArtifacts");

    private String action;

    NexusScriptAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
    }