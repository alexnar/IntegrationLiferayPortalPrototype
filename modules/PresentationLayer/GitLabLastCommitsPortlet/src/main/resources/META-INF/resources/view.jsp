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
                <th>getTitle</th>
                <th>getMessage</th>
                <th>getAuthorName</th>
                <th>getCommittedDate</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${lastCommits}" var="commit" varStatus="loop">
                <tr>
                    <td>${commit.title}</td>
                    <td>${commit.message}</td>
                    <td>${commit.authorName}</td>
                    <td>${commit.committedDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
