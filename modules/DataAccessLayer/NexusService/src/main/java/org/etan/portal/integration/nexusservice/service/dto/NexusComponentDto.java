package org.etan.portal.integration.nexusservice.service.dto;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Naryzhny Alex
 */
public class NexusComponentDto implements Comparable<NexusComponentDto> {
    @SerializedName("Name")
    private String name;

    @SerializedName("LastUpdated")
    private Date lastUpdated;

    @SerializedName("Group")
    private String group;

    @SerializedName("Version")
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int compareTo(NexusComponentDto nexusComponentDto) {
        Date thisLastUpdated = this.getLastUpdated();
        Date anotherLastUpdated = nexusComponentDto.getLastUpdated();
        return thisLastUpdated.compareTo(anotherLastUpdated);
    }

}
