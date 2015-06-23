<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title><fmt:message key="index.title.mainPage"/></title>
  <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<%@ include file="/pages/include/header.jsp" %>

<div id="wrapper">

  <main>
    <div id="content">
      <div class="innertube">
        <h1><fmt:message key="index.form.authorisation.welcome"/></h1>

        <p>

        <form action="Controller" method="post" class="form-container">
          <div class="form-title"><h2><fmt:message key="index.form.authorisation"/></h2></div>
          <div class="form-title"><fmt:message key="index.form.login"/></div>
          <input class="form-field" type="text" name="login" pattern="[\w]{3,8}" required /><br/>

          <div class="form-title"><fmt:message key="index.form.password"/></div>
          <input class="form-field" type="password" name="password" pattern="[\w]{3,8}" required /><br/>

          <div class="submit-container">
            <input type="hidden" name="page" value="login"/>
            <input class="submit-button" type="submit" value=<fmt:message key="index.form.button"/>>
          </div>
        </form>
        </p>
      </div>
    </div>
  </main>

  <nav id="nav">
    <div class="innertube">
      <%@include file="/pages/include/language.jsp" %>
      <%@include file="/pages/include/newuser.jsp" %>

    </div>
  </nav>

</div>

<%@ include file="/pages/include/footer.jsp" %>

</body>
</html>