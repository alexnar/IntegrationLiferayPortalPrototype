package org.etan.portal.integration.nexusservice.service.script;

import com.google.gson.Gson;
import org.etan.portal.integration.nexusservice.service.dto.NexusScriptDto;

/**
 * @author Naryzhny Alex
 */
public class NexusScripts {
    private static final String SCRIPT_TYPE = "groovy";
    public NexusScriptDto getCreateMavenRepositoryScript(String repositoryName) {
        String script = "" +
                "import org.sonatype.nexus.blobstore.api.BlobStoreManager;\n" +
                "import org.sonatype.nexus.repository.storage.WritePolicy;\n" +
                "import org.sonatype.nexus.repository.maven.VersionPolicy;\n" +
                "import org.sonatype.nexus.repository.maven.LayoutPolicy;\n" +
                "\n" +
                "def argsMap = args.split('&').inject([:]) { map, token ->\n" +
                "    token.split('=').with { map[it[0]] = it[1] }\n" +
                "    map\n" +
                "}\n" +
                "\n" +
                "def repositoryName = argsMap.getAt(\"repositoryName\")\n" +
                "\n" +
                "repository.createMavenHosted(repositoryName, BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true,\n" +
                "        VersionPolicy.MIXED, WritePolicy.ALLOW, LayoutPolicy.STRICT)";

        NexusScriptDto nexusScriptDto = new NexusScriptDto.Builder().setScriptName(repositoryName).
                setScriptType(SCRIPT_TYPE).setScriptContent(script).build();
        return nexusScriptDto;
    }

    public NexusScriptDto getAssignUserToRepositoryScript(String userId, String repositoryId) {
        return null;
    }

    public NexusScriptDto getUnassignUserToRepositoryScript(String userId, String repositoryId) {
        return null;
    }

    public NexusScriptDto getLastArtifactsScript(String repositoryId, int artifactsCount) {
        return null;
    }

}
