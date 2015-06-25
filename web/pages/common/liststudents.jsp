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
                            <c:if test="${student.id==archive.userId}">
                                <c:if test="${student.role==2}">
                                    <fmt:message key="list.students.teacher"/>
                                </c:if>
                                <c:if test="${student.role==3}">
                                    <fmt:message key="list.students.student"/>
                                </c:if>
                                <div class="form-title"><c:out value="${student.name}"/> <c:out
                                        value="${student.surname}"/>
                                    <c:if test="${user.role==2}">
                                        <c:if test="${student.role!=2}">
                                            <c:if test="${archive.mark==0}">
                                                <c:set var="nomark" value="true"/>
                                                <input class="form-smallfield" type="text" name="${student.id}"
                                                       pattern="^10$|^[1-9]$"
                                                       required/>
                                            </c:if>
                                            <c:if test="${archive.mark!=0}">
                                                <c:out value="${archive.mark}"/>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${user.role==1}">
                                        <c:if test="${student.role==3}">
                                            <c:out value="${archive.mark}"/>
                                        </c:if>
                                        <c:if test="${studentsListSize==1}">
                                            <div class="submit-container">
                                                <input type="hidden" name="teacherId" value="${student.id}">
                                                <input type="hidden" name="page" value="delete_course">
                                                <button class="submit-button" type="submit"><fmt:message key="list.students.delete.course"/></button>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:forEach>

                    <c:if test="${user.role==2}">
                        <c:if test="${nomark==true}">
                            <div class="submit-container">
                                <input type="hidden" name="page" value="set_marks"/>
                                <button class="submit-button" type="submit"><fmt:message
                                        key="list.students.set.mark"/></button>
                            </div>
                        </c:if>
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
