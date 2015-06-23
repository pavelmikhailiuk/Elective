<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="error.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<header id="header">
    <div class="innertube">
        <h1><fmt:message key="error.form.sorry"/></h1>
    </div>
</header>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="error.form.error"/></h1>

                <p> Request from ${pageContext.errorData.requestURI} is failed </p>

                <p> Servlet name or type: ${pageContext.errorData.servletName} </p>

                <p> Status code: ${pageContext.errorData.statusCode} </p>

                <p>Exception: ${pageContext.errorData.throwable} </p>
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