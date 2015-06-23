<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="user.error.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@include file="../include/header.jsp" %>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="user.error.message"/></h1>

                <p>
                    <%@include file="../include/newuser.jsp" %>
                <h3><fmt:message key="user.back.login.link"/></h3>

                <form action="Controller" method="post">
                    <input type="hidden" name="page" value="logout"/>
                    <ul>
                        <li><input type="submit" class='button' value=<fmt:message key="user.back.login.link"/>></li>
                    </ul>
                </form>
                </p>
            </div>
        </div>
    </main>
</div>

<%@ include file="/pages/include/footer.jsp" %>

</body>
</html>