<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="registration.error.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<header id="header">
    <div class="innertube">
        <h1><fmt:message key="registration.error.title"/></h1>
    </div>
</header>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="registration.error.title"/></h1>

                <p>
                    <%@include file="../include/newuser.jsp" %>
                </p>
            </div>
        </div>
    </main>
    <nav id="nav">
        <div class="innertube">

        </div>
    </nav>
</div>

<%@ include file="/pages/include/footer.jsp" %>

</body>
</html>