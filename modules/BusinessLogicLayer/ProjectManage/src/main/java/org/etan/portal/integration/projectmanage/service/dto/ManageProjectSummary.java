package org.etan.portal.integration.projectmanage.service.dto;

import java.util.Map;

/**
 * Summary containing result of
 * managing project operations.
 *
 * @author Naryzhny Alex
 */
public class ManageProjectSummary {
    private Map<String, Boolean> summaryMap;
    private boolean success;

    public Map<String, Boolean> getSummaryMap() {
        return summaryMap;
    }

    public void setSummaryMap(Map<String, Boolean> summaryMap) {
        this.summaryMap = summaryMap;
    }

    public boolean isSuccess() {
        for (Map.Entry<String, Boolean> summary : summaryMap.entrySet()) {
             boolean success = summary.getValue();
             if (!success) {
                 return false;
             }
        }

        return true;
    }
}
