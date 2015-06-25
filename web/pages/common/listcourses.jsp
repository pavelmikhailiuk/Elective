<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="list.courses.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@ include file="/pages/include/header.jsp" %>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="list.courses.title"/></h1>

                <p>
                    <c:forEach var="course" items="${coursesList}">

                <form action="Controller" method="post" class="form-bigcontainer">
                    <div class="form-title"><fmt:message key="list.courses.course.name"/> <c:out
                            value="${course.name}"/> <fmt:message key="list.courses.start.date"/> <fmt:formatDate
                            value="${course.startDate}"/> <fmt:message key="list.courses.end.date"/> <fmt:formatDate
                            value="${course.endDate}"/></div>
                    <div class="submit-container">
                        <input type="hidden" name="courseId" value="${course.id}"/>
                        <c:if test="${user.role==1}">
                            <input type="hidden" name="page" value="list_students"/>
                            <button class="submit-button" type="submit"><fmt:message
                                    key="list.courses.teacher.view.students"/></button>
                        </c:if>
                        <c:if test="${user.role==2}">
                            <c:if test="${course.statusId==0}">
                                <input type="hidden" name="page" value="end_course"/>
                                <button class="submit-button" type="submit"><fmt:message
                                        key="list.courses.teacher.end"/></button>
                            </c:if>
                            <c:if test="${course.statusId==1}">
                                <input type="hidden" name="page" value="list_students"/>
                                <button class="submit-button" type="submit"><fmt:message
                                        key="list.courses.teacher.set.mark"/></button>
                            </c:if>
                            <c:if test="${course.statusId==2}">
                                <input type="hidden" name="page" value="list_students"/>
                                <button class="submit-button" type="submit"><fmt:message
                                        key="list.courses.teacher.view.students"/></button>
                            </c:if>
                        </c:if>
                        <c:if test="${user.role==3}">
                            <c:if test="${courseStatus==0}">
                                <input type="hidden" name="page" value="join_course"/>
                                <input class="submit-button" type="submit" value=<fmt:message key="list.courses.join"/>>
                            </c:if>
                            <c:if test="${courseStatus==10}">
                                <input type="hidden" name="page" value="escape_course"/>
                                <input class="submit-button" type="submit" value=<fmt:message key="list.courses.escape"/>>
                            </c:if>

                        </c:if>
                    </div>
                </form>
                </c:forEach>
                </p>
            </div>
        </div>
    </main>
    <nav id="nav">
        <div class="innertube">
            <br>
            <%@include file="../include/back.jsp" %>
            <br>
            <%@ include file="/pages/include/logout.jsp" %>

        </div>
    </nav>
</div>

<%@ include file="/pages/include/footer.jsp" %>

</body>

</html>
