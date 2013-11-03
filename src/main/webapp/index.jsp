<%@ page import="com.kadet.HandBook.util.DataStrings" %>
<%@ page import="com.kadet.HandBook.util.Messages" %>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<html>
<head>

    <link rel="stylesheet" type="text/css" media="screen"
          href="/web-resources/bootstrap/css/bootstrap.min.css"/>


</head>

<body>

<div class="row">
    <div class="pagination-centered well">
        <h1>
            <%=Messages.APP_TITLE%>
        </h1>
    </div>
</div>

<br>

<div class="row">
    <div class="span3 well">
        <a href="/facebook" class="btn btn-default">
            <img src="/web-resources/images/Facebook.png" width="32">
        </a>
    </div>
</div>

</body>
</html>