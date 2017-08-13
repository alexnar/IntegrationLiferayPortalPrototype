<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="GitLabLastCommitsPortlet.caption"/></b>
</p>

<br/>

<div style="
    border: 1px solid black;
    border-radius: 16px;
    ">
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Title</th>
                <th>Message</th>
                <th>AuthorName</th>
                <th>CommittedDate</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty lastCommits}">
                <tr>
                    <td colspan="4" style="text-align: center;">There are still no commits to display</td>
                </tr>
            </c:if>
            <c:if test="${not empty lastCommits}">
                <c:forEach items="${lastCommits}" var="commit" varStatus="loop">
                        <tr>
                            <td>${commit.title}</td>
                            <td>${commit.message}</td>
                            <td>${commit.authorName}</td>
                            <td>${commit.committedDate}</td>
                        </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
</div>
