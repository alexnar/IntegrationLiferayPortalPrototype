<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="ProjectManagePortlet.caption"/></b>
</p>

<portlet:actionURL name='changeUsersMembership' var="actionUrl"/>


<aui:form action="${actionUrl}" method="post" name="myForm">

    <aui:button type="submit" class="btn btn-primary active" value="Change users membership" />
<br/>
<br/>

    <div style="
        border: 1px solid black;
        border-radius: 16px;
        ">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Full name</th>
                    <th>Roles</th>
                    <th>Member</th>
                    <th>add \ delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userList}" var="user" varStatus="loop">
                    <c:if test="${not isMemberList[loop.index]}">
                        <tr>
                    </c:if>
                    <c:if test="${isMemberList[loop.index]}">
                        <tr style="background:gray;" >
                    </c:if>
                        <td>${user.fullName}</td>
                        <td>
                            <c:forEach items="${user.roles}" var="role">
                                ${role.name};
                            </c:forEach>
                        </td>
                        <td>${isMemberList[loop.index]}</td>
                        <td>
                            <c:if test="${not isMemberList[loop.index]}">
                                <aui:input type="checkbox" id="forAssign${user.userId}" name="forAssign" value="${user.userId}"/>
                            </c:if>
                            <c:if test="${isMemberList[loop.index]}">
                                <aui:input type="checkbox" id="forUnassign${user.userId}" name="forUnassign" value="${user.userId}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </aui:form>
</div>