<%@ include file="init.jsp" %>



<portlet:actionURL name='createProject' var="actionUrl"/>
<aui:form action="<%= actionUrl %>" method="post" name="myForm">
        <aui:input label="Project name:" name="projectName" type="text"/>

        <aui:button type="submit" class="btn btn-primary active" value="Create"
         onClick="return confirm('A Message you want to give it to the end user ?');" />
</aui:form>
${createdProject}
