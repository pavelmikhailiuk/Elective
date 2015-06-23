<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="add.course.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@ include file="/pages/include/header.jsp" %>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="add.course.list"/></h1>

                <p>
                    <c:forEach var="teacher" items="${teachersList}">

                <form action="Controller" method="post" class="form-container">
                    <div class="form-title"><c:out value="${teacher.name}"/> <c:out value="${teacher.surname}"/></div>
                    <div class="submit-container">
                        <input type="hidden" name="teacherId" value="${teacher.id}"/>
                        <input type="hidden" name="teacherName" value="${teacher.name}"/>
                        <input type="hidden" name="teacherSurname" value="${teacher.surname}"/>
                        <input type="hidden" name="page" value="new_course"/>
                        <button class="submit-button" type="submit"><fmt:message key="add.course.button"/></button>
                    </div>
                </form>
                </c:forEach>
                </p>
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
