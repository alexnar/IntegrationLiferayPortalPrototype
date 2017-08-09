<%@ include file="init.jsp" %>

<p>

	<portlet:actionURL name='createProject' var="actionUrl"/>
	<aui:form action="<%= actionUrl %>" method="post" name="myForm">
            <aui:input label="Project name" name="projectName" type="text"/>

            <aui:button type="submit" class="btn btn-primary active">Create</aui:button>
    </aui:form>
    ${createdProject}
</p>