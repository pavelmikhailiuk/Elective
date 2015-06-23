<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <c:if test="${user.role==1}">
        <title><fmt:message key="admin.title"/></title>
    </c:if>
    <c:if test="${user.role==2}">
        <title><fmt:message key="teacher.title"/></title>
    </c:if>
    <c:if test="${user.role==3}">
        <title><fmt:message key="student.title"/></title>
    </c:if>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>
<%@include file="../include/header.jsp" %>
<div id="wrapper">
    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="index.form.welcome"/></h1>

                <p>
                </p>
            </div>
        </div>
    </main>
    <nav id="nav">
        <div class="innertube">
            <c:if test="${user.role==1}">
                <h3><fmt:message key="admin.add.new.course"/></h3>

                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_teachers"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message key="admin.new.course.button"/></button>
                        </li>
                    </ul>
                </form>
                <%@include file="../include/newuser.jsp" %>

                <h3><fmt:message key="list.courses"/></h3>

                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="2"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message key="list.end.courses.button"/></button>
                        </li>
                    </ul>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="10"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message
                                    key="list.acting.courses.button"/></button>
                        </li>
                    </ul>
                </form>
            </c:if>
            <c:if test="${user.role==3}">
                <h3><fmt:message key="list.courses"/></h3>

                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="2"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message key="list.end.courses.button"/></button>
                        </li>
                    </ul>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="0"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message key="list.join.courses.button"/></button>
                        </li>
                    </ul>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="10"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message
                                    key="list.escape.courses.button"/></button>
                        </li>
                    </ul>
                </form>
            </c:if>
            <c:if test="${user.role==2}">
                <h3><fmt:message key="list.courses"/></h3>

                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="2"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message key="list.end.courses.button"/></button>
                        </li>
                    </ul>
                </form>
                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="list_courses"/>
                    <input type="hidden" name="status" value="10"/>
                    <ul>
                        <li>
                            <button type="submit" class='button'><fmt:message
                                    key="list.acting.courses.button"/></button>
                        </li>
                    </ul>
                </form>
            </c:if>
            <%@include file="../include/logout.jsp" %>
        </div>
    </nav>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>