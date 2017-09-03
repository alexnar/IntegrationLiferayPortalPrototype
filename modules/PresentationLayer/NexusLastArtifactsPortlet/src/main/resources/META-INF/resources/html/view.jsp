<%@ include file="init.jsp" %>

<p>
	<b><liferay-ui:message key="NexusLastArtifactsPortlet.caption"/></b>
</p>


<br/>

<div id="artifacts-content">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Group</th>
                <th>Version</th>
                <th>Last updated</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty lastArtifacts}">
                <tr>
                    <td colspan="4" style="text-align: center;">There are still no artifacts to display</td>
                </tr>
            </c:if>
            <c:if test="${not empty lastArtifacts}">
                <c:forEach items="${lastArtifacts}" var="artifact" varStatus="loop">
                    <tr>
                        <td>${artifact.name}</td>
                        <td>${artifact.group}</td>
                        <td>${artifact.version}</td>
                        <td>${artifact.lastUpdated}</td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</div>