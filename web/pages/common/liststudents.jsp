<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="list.students.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@ include file="/pages/include/header.jsp" %>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="list.students.list"/></h1>

                <p>

                <form action="Controller" method="post" class="form-container">
                    <c:forEach var="student" items="${studentsList}">
                        <c:forEach var="archive" items="${marksList}">
                            <c:if test="${user.role==2}">
                                <div class="form-title"><c:out value="${student.name}"/> <c:out
                                        value="${student.surname}"/> <input class="form-smallfield" type="text"
                                                                            name="${student.id}" pattern="^10$|^[1-9]$"
                                                                            required/>
                                </div>
                            </c:if>
                            <c:if test="${user.role==1}">
                                <div class="form-title"><c:out value="${student.name}"/> <c:out
                                        value="${student.surname}"/> <c:out value="${archive.mark}"/>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <c:if test="${user.role==2}">
                        <div class="submit-container">
                            <input type="hidden" name="page" value="set_marks"/>
                            <button class="submit-button" type="submit"><fmt:message
                                    key="list.students.set.mark"/></button>
                        </div>
                    </c:if>

                </form>

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
