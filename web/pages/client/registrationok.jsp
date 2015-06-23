<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="registration.ok.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<header id="header">
    <div class="innertube">
        <h1><fmt:message key="registration.ok.title"/></h1>
    </div>
</header>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="registration.ok.title"/></h1>

                <p>

                <form action="Controller" method="post" class="form-container">
                    <div class="form-title"><fmt:message key="registration.ok.message"/></div>
                    <div class="submit-container">
                        <input type="hidden" name="page" value="user"/>
                        <input class="submit-button" type="submit" value=<fmt:message key="registration.ok.button"/>>
                    </div>
                </form>
                </p>
            </div>
        </div>
    </main>
    <nav id="nav">
        <div class="innertube">

            <h3><fmt:message key="index.logout.user"/></h3>

            <form action="Controller" method="post">
                <input type="hidden" name="page" value="logout"/>
                <ul>
                    <li><input type="submit" class='button' value=<fmt:message key="logout.user.button"/>></li>
                </ul>
            </form>
        </div>
    </nav>
</div>

<%@ include file="/pages/include/footer.jsp" %>

</body>
</html>