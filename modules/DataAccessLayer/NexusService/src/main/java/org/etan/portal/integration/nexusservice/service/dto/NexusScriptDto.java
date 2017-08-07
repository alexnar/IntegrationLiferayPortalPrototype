package org.etan.portal.integration.nexusservice.service.dto;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Script DTO.
 * Implements Builder pattern.
 *
 * @author Naryzhny Alex
 */
public class NexusScriptDto {
    @SerializedName("name")
    private String scriptName;

    @SerializedName("type")
    private String scriptType;

    @SerializedName("content")
    private String scriptContent;

    public String getScriptName() {
        return scriptName;
    }

    public String getScriptType() {
        return scriptType;
    }

    public String getScriptContent() {
        return scriptContent;
    }


    private NexusScriptDto(Builder builder) {
        this.scriptName = builder.scriptName;
        this.scriptType = builder.scriptType;
        this.scriptContent = builder.scriptContent;
    }

    /**
     * Builder class for implementing
     * Builder pattern.
     */
    public static class Builder {
        private String scriptName;
        private String scriptType;
        private String scriptContent;

        public Builder setScriptName(String scriptName) {
            this.scriptName = scriptName;
            return this;
        }

        public Builder setScriptType(String scriptType) {
            this.scriptType = scriptType;
            return this;
        }

        public Builder setScriptContent(String scriptContent) {
            this.scriptContent = scriptContent;
            return this;
        }


        public NexusScriptDto build() {
            return new NexusScriptDto(this);
        }
    }
}
