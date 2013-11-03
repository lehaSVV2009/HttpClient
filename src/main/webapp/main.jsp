<%@ page import="com.kadet.HandBook.util.Messages" %>
<%@ page import="com.kadet.HandBook.entity.Chapter" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Кадет
  Date: 23.10.13
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title></title>

    <link rel="stylesheet" type="text/css" media="screen"
          href="/web-resources/bootstrap/css/bootstrap.min.css"/>
    <script src="/web-resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="/web-resources/bootstrap/js/jquery.min.js"></script>


</head>
<body>


<br>

<%
    String name = null;
    String surname = null;

    Cookie[] cookies = request.getCookies();%>
<%
    for (Cookie cookie : cookies) {
        if ("userName".equals(cookie.getName())) {
            name = cookie.getValue();
        }
        if ("userSurname".equals(cookie.getName())) {
            surname = cookie.getValue();
        }
    }
%>

<%if (name != null && surname != null) {%>

<div class="row pagination-centered">
    <div class="span3 well">
        <i>
            <%=URLDecoder.decode(name, "UTF-8")%>
        </i>
        <br>
        <i>
            <%=URLDecoder.decode(surname, "UTF-8")%>
        </i>
        <br>
    </div>
</div>


<div class="row">

    <div class="span3 well">

        <form action="/openChapter" method="GET" role="form">
            <%List<Chapter> chapters = (List<Chapter>) request.getAttribute("chapters");%>
            <%if (chapters != null) {%>

            <select name="chapterId" multiple class="form-control">
                <%for (Chapter chapter : chapters) {%>
                <option value="<%=chapter.getId()%>">
                    <%=chapter.getTitle()%>
                </option>
                <%}%>
            </select>

            <%}%>

            <input type="submit" value="<%=Messages.OPEN_CHAPTER_BUTTON_TEXT%>" class="btn btn-primary"/>
        </form>

    </div>

    <div class="span6 well" itemscope itemtype="http://schema.org/Book">
        <%String title = (String) request.getAttribute("title");%>
        <%String text = (String) request.getAttribute("text");%>

        <%if (title != null && text != null) {%>
        <h2>
            <b>
                <span itemprop="name">
                    <%=title%>
                </span>
            </b>
        </h2>
        <br>
        <span itemprop="text">
            <%=text%>
        </span>
        <%}%>
    </div>

    <div class="span3 well">

        <div class="row">
            <div class="span2 well">
                <a href="addChapter.jsp" class="btn btn-default">Добавление</a>
            </div>
        </div>
        <div class="row">
            <div class="span2 well">
                <a href="/goToRemoveChapter" class="btn btn-default">Удаление</a>
            </div>
        </div>
        <div class="row">
            <div class="span2 well">
                <a href="/goToEditChapter" class="btn btn-default">Редактирование</a>
            </div>
        </div>

    </div>

</div>

<br>
<br>

<%} else { %>

<div class="pagination-centered row">
    <div class="span 6 well control-group success">
        <%=Messages.NOT_REGISTERED%>
    </div>
</div>

<%}%>


</body>
</html>