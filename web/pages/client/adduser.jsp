<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><fmt:message key="add.user.title"/></title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@include file="/pages/include/header.jsp" %>

<div id="wrapper">

    <main>
        <div id="content">
            <div class="innertube">
                <h1><fmt:message key="add.user.title"/></h1>

                <p>

                <form action="Controller" method="post" class="form-container">
                    <input type="hidden" name="page" value="register_new_user"/>

                    <div class="form-title"><h2><fmt:message key="add.user.formMessage"/></h2></div>

                    <div class="form-title"><fmt:message key="add.user.form.username"/></div>
                    <input class="form-field" type="text" name="username" pattern="([A-Z][a-z]+)|([А-Я][а-я]+)"
                           required/><br/>

                    <div class="form-title"><fmt:message key="add.user.form.surname"/></div>
                    <input class="form-field" type="text" name="surname" pattern="([A-Z][a-z]+)|([А-Я][а-я]+)"
                           required/><br/>

                    <div class="form-title"><fmt:message key="add.user.form.login"/></div>
                    <input class="form-field" type="text" name="login" pattern="[\w]{3,8}" required/><br/>

                    <div class="form-title"><fmt:message key="add.user.form.password"/></div>
                    <input class="form-field" type="password" name="password" pattern="[\w]{3,8}" required/><br/>

                    <div class="submit-container">
                        <button class="submit-button" type="submit"><fmt:message key="index.new.user.button"/></button>
                    </div>
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