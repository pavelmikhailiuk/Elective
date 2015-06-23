<%@ include file="/pages/include/taglib.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title><fmt:message key="course.ok.title"/></title>
  <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
</head>

<body>

<header id="header">
  <div class="innertube">
    <h1><fmt:message key="course.ok.title"/></h1>
  </div>
</header>

<div id="wrapper">

  <main>
    <div id="content">
      <div class="innertube">
        <h1><fmt:message key="course.ok.title"/></h1>

        <p>

        <form action="Controller" method="post" class="form-container">
          <div class="form-title"><fmt:message key="course.ok.message"/></div>
          <div class="submit-container">
            <input type="hidden" name="page" value="list_teachers"/>
            <button class="submit-button" type="submit"><fmt:message key="course.ok.button"/></button>
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
