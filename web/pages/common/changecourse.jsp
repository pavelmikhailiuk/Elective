<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="change.course.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@ include file="/pages/include/header.jsp" %>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="change.course.list"/></h1>
                <fmt:message key="change.course.course.name"/><c:out value="${course.name}"/>
                <fmt:message key="change.course.start.date"/><fmt:formatDate value="${course.startDate}"/>
                <fmt:message key="change.course.end.date"/><fmt:formatDate value="${course.endDate}"/>
                <fmt:message key="change.course.teacher"/><c:out value="${oldTeacher.name}"/> <c:out
                    value="${oldTeacher.surname}"/>

                <c:forEach var="teacher" items="${teachersList}">

                    <form action="Controller" method="post" class="form-container">
                        <div class="form-title"><c:out value="${teacher.name}"/> <c:out
                                value="${teacher.surname}"/></div>
                        <div class="submit-container">
                            <input type="hidden" name="oldTeacherId" value="${oldTeacher.id}"/>
                            <input type="hidden" name="teacherId" value="${teacher.id}"/>
                            <input type="hidden" name="courseId" value="${course.id}"/>
                            <input type="hidden" name="page" value="set_course"/>
                            <button class="submit-button" type="submit"><fmt:message
                                    key="choose.teacher.button"/></button>
                        </div>
                    </form>
                </c:forEach>

            </div>
        </div>
    </main>
    <form action="Controller" method="post">
        <nav id="nav">
            <div class="innertube">

                <%@include file="../include/logout.jsp" %>
                <br>
                <%@include file="../include/back.jsp" %>
            </div>
        </nav>
    </form>
</div>

<%@ include file="/pages/include/footer.jsp" %>

</body>

</html>
