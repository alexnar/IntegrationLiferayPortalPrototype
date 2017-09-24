<%@ include file="init.jsp" %>

<portlet:actionURL name='createProject' var="createProjectUrl"/>
<portlet:actionURL name='setProjectName' var="setProjectName"/>
<portlet:resourceURL id="addResUrl" var="addResUrl"/>
<portlet:resourceURL var="testAjaxResourceUrl"></portlet:resourceURL>


<aui:form name="fm" method="POST" id="formId" action="<%=createProjectUrl.toString()%>">

    <aui:input label="Project name:" name="projectName" type="text"/>


    <aui:button value="Save" key="save" onClick="javascript:showAddNoteDialog();">

    </aui:button>

</aui:form>
<div class="yui3-skin-sam">
    <div id="modal"></div>
</div>