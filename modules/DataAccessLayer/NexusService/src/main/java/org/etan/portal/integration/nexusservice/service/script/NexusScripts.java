package org.etan.portal.integration.nexusservice.service.script;

import com.google.gson.Gson;
import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;

/**
 * Contains methods which return
 * nexus api scripts for doing some
 * action in Nexus Repository
 * @author Naryzhny Alex
 */
public class NexusScripts {
    private static final String SCRIPT_TYPE = "groovy";

    /**
     * Script for creation Maven hosted repository.
     *
     * @return - script DTO
     */
    public NexusScriptDto getCreateMavenRepositoryScript() {
        String script = "" +
                "import org.sonatype.nexus.blobstore.api.BlobStoreManager;\n" +
                "import org.sonatype.nexus.repository.storage.WritePolicy;\n" +
                "import org.sonatype.nexus.repository.maven.VersionPolicy;\n" +
                "import org.sonatype.nexus.repository.maven.LayoutPolicy;\n" +
                "\n" +
                "// Getting arguments from Post parameters\n" +
                "def argsMap = args.split('&').inject([:]) { map, token ->\n" +
                "    token.split('=').with { map[it[0]] = it[1] }\n" +
                "    map\n" +
                "}\n" +
                "\n" +
                "def repositoryName = argsMap.getAt(\"repositoryName\").toString()\n" +
                "repository.createMavenHosted(repositoryName, BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true,\n" +
                "        VersionPolicy.MIXED, WritePolicy.ALLOW, LayoutPolicy.STRICT)\n";
        String scriptName = NexusScriptAction.CREATE_MAVEN_HOSTED.getAction();
        NexusScriptDto nexusScriptDto = new NexusScriptDto.Builder().setScriptName(scriptName).
                setScriptType(SCRIPT_TYPE).setScriptContent(script).build();
        return nexusScriptDto;
    }

    /**
     * Script for assign user to repository.
     *
     * @return - script DTO
     */
    public NexusScriptDto getAssignUserToRepositoryScript() {
        String script = "" +
                "import org.sonatype.nexus.security.user.UserManager;\n" +
                "import org.sonatype.nexus.security.role.NoSuchRoleException;\n" +
                "\n" +
                "def argsMap = args.split('&').inject([:]) { map, token ->\n" +
                "    token.split('=').with { map[it[0]] = it[1] }\n" +
                "    map\n" +
                "};\n" +
                "\n" +
                "def roleName = argsMap.getAt(\"roleName\").toString();\n" +
                "def privilegeName = argsMap.getAt(\"privilegeName\").toString();\n" +
                "def userId = argsMap.getAt(\"userId\").toString();\n" +
                "def user = security.securitySystem.getUser(userId);\n" +
                "\n" +
                "def authManager = security.getSecuritySystem().getAuthorizationManager(UserManager.DEFAULT_SOURCE);\n" +
                "def role = null;\n" +
                "try {\n" +
                "    role = authManager.getRole(roleName);\n" +
                "} catch (NoSuchRoleException e) {\n" +
                "    print \"There is no such role, it will be created!\";\n" +
                "}\n" +
                "List<String> privileges = new ArrayList<>();\n" +
                "privileges.add(privilegeName);\n" +
                "\n" +
                "if (role == null) {\n" +
                "    role = security.addRole(roleName, roleName, \"\", privileges, new ArrayList<String>());\n" +
                "}\n" +
                "List<String> roles = new ArrayList<>();\n" +
                "roles.add(role.getRoleId());\n" +
                "user.getRoles().each {\n" +
                "    roles.add(it.getRoleId());\n" +
                "};\n" +
                "user = security.setUserRoles(userId, roles);\n" +
                "return user;";

        String scriptName = NexusScriptAction.ASSIGN_USER.getAction();
        NexusScriptDto nexusScriptDto = new NexusScriptDto.Builder().setScriptName(scriptName).
                setScriptType(SCRIPT_TYPE).setScriptContent(script).build();
        return nexusScriptDto;
    }

    /**
     * Script for unassign user to repository.
     *
     * @return - script DTO
     */
    public NexusScriptDto getUnassignUserToRepositoryScript() {
        String script = "" +
                "def argsMap = args.split('&').inject([:]) { map, token ->\n" +
                "    token.split('=').with { map[it[0]] = it[1] }\n" +
                "    map\n" +
                "};\n" +
                "\n" +
                "def roleName = argsMap.getAt(\"roleName\").toString();\n" +
                "def userId = argsMap.getAt(\"userId\").toString();\n" +
                "def user = security.securitySystem.getUser(userId);\n" +
                "\n" +
                "List<String> roles = new ArrayList<>();\n" +
                "user.getRoles().each {\n" +
                "    if (it.getRoleId() != roleName) {\n" +
                "        roles.add(it.getRoleId());\n" +
                "    }\n" +
                "};\n" +
                "user = security.setUserRoles(userId, roles);\n" +
                "return user;";
        String scriptName = NexusScriptAction.UNASSIGN_USER.getAction();
        NexusScriptDto nexusScriptDto = new NexusScriptDto.Builder().setScriptName(scriptName).
                setScriptType(SCRIPT_TYPE).setScriptContent(script).build();
        return nexusScriptDto;
    }

    /**
     * Script for checking create repository
     * opportunity.
     *
     * @return - script DTO
     */
    public NexusScriptDto getCheckCreateRepositoryOpportunityScript() {
        String script ="" +
                "def argsMap = args.split('&').inject([:]) { map, token ->\n" +
                "    token.split('=').with { map[it[0]] = it[1] }\n" +
                "    map\n" +
                "}\n" +
                "\n" +
                "def repositoryName = argsMap.getAt(\"repositoryName\").toString();\n" +
                "def repo = repository.repositoryManager.get(repositoryName);\n" +
                "if (repo != null) {\n" +
                "    return \"REPOSITORY_EXISTS\";\n" +
                "} else {\n" +
                "    return \"REPOSITORY_NOT_EXISTS\"\n" +
                "}";
        String scriptName = NexusScriptAction.CHECK_CREATE_REPOSITORY_OPPORTUNITY.getAction();
        NexusScriptDto nexusScriptDto = new NexusScriptDto.Builder().setScriptName(scriptName).
                setScriptType(SCRIPT_TYPE).setScriptContent(script).build();
        return nexusScriptDto;
    }

    public NexusScriptDto getLastArtifactsScript() {
        String script = "" +
                "import groovy.json.JsonBuilder\n" +
                "import org.sonatype.nexus.repository.storage.StorageFacet;\n" +
                "import org.sonatype.nexus.repository.storage.Query;\n" +
                "\n" +
                "def argsMap = args.split('&').inject([:]) { map, token ->\n" +
                "    token.split('=').with { map[it[0]] = it[1] }\n" +
                "    map\n" +
                "}\n" +
                "\n" +
                "def repositoryName = argsMap.getAt(\"repositoryName\").toString();\n" +
                "def repo = repository.repositoryManager.get(repositoryName);\n" +
                "StorageFacet storageFacet = repo.facet(StorageFacet);\n" +
                "def tx = storageFacet.txSupplier().get();\n" +
                "\n" +
                "tx.begin();\n" +
                "\n" +
                "def components = tx.findComponents(Query.builder().build(), [repo]);\n" +
                "\n" +
                "def jsonBuilder = new JsonBuilder();\n" +
                "\n" +
                "jsonBuilder (\n" +
                "        components.collect {\n" +
                "            [Name: it.name(), Version: it.version(), Group: it.group(), LastUpdated: it.lastUpdated().toString()]\n" +
                "        }\n" +
                ");\n" +
                "\n" +
                "\n" +
                "tx.close();\n" +
                "\n" +
                "return jsonBuilder;";
        String scriptName = NexusScriptAction.LAST_ARTIFACTS.getAction();
        NexusScriptDto nexusScriptDto = new NexusScriptDto.Builder().setScriptName(scriptName).
                setScriptType(SCRIPT_TYPE).setScriptContent(script).build();
        return nexusScriptDto;
    }

}
