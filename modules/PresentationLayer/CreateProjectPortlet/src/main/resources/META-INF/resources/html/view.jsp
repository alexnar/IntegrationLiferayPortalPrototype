<%@ include file="init.jsp" %>

<portlet:actionURL name='createProject' var="createProjectUrl"/>
<portlet:actionURL name='setProjectName' var="setProjectName"/>
<portlet:resourceURL id="addResUrl" var="addResUrl"/>
<portlet:resourceURL var="testAjaxResourceUrl"></portlet:resourceURL>



${renderRequest}
${renderRequest.getPortletSession()}
${renderRequest.getPortletSession().getAttribute("projectName")}
<h1>Summary</h1>

${projectManageSummary}


<aui:form name="fm" method="POST" id="formId" action="<%=createProjectUrl.toString()%>" >

<aui:input label="Project name:" name="projectName" type="text"/>


<aui:button value="Save" key="save" onClick="javascript:showAddNoteDialog();">

</aui:button>

</aui:form>
<div class="yui3-skin-sam">
  <div id="modal"></div>
</div>

<script>

function showAddNoteDialog(){
   //var xhr = new XMLHttpRequest();
   //xhr.open('POST', '${addResUrl}');
   //xhr.send("hello");
   //alert(${projectName});
   var value;
   //var form = document.getElementById("<portlet:namespace/>projectName");
   //alert(form.value);
   AUI().use('aui-io-request', function(A){
    A.io.request('${testAjaxResourceUrl}', {
       method: 'POST',
       data: {
        "<portlet:namespace />projectName" :  document.getElementById("<portlet:namespace/>projectName").value
       },
       on: {
          success: function(data) {
                       // Run the code here that needs
                       //    to access the data returned
                       var resp = this.get('responseData');
                       checkName( resp );
                       return data;
                   }
         }
    });
   });

function checkName( data ) {
 var statusContent = '';
 var dataJson = JSON.parse(data);
 Object.keys(dataJson).forEach(function(key){
        var glyphIconType;
     if (dataJson[key] === true) {
        glyphIconType = "glyphicon-ok";
     } else {
        glyphIconType = "glyphicon-remove";
     }
        statusContent += "<p>"+ key + " : " + '<span class="glyphicon ' + glyphIconType +
                        '"></span>' + "</p>";;
 });

   YUI().use('aui-modal', function(Y) {
       var modal = new Y.Modal(
         {
           bodyContent: statusContent,
           centered: true,
           headerContent: '<h2>Summary of where project will be created</h2>',
           modal: true,
           render: '#modal',
           width: 500
         }
       ).render();
        modal.addToolbar(
              [
                {
                  label: '<liferay-ui:message key="Cancel"/>',
                  on: {
                    click: function() {
                     modal.hide();
                    }
                  }
                },
                {
                   label: '<liferay-ui:message key="Create"/>',
                   on: {
                     click: function() {
                      modal.hide();
                      document.getElementById("<portlet:namespace/>fm").submit();
                     }
                   }
                 },
                ]
       );
     }
   );
    }
}
</script>