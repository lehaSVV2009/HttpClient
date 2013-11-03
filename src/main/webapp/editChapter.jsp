<%@ page import="com.kadet.HandBook.entity.Chapter" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kadet.HandBook.util.Messages" %>
<%@ page import="java.net.URLDecoder" %>
<%--
  Created by IntelliJ IDEA.
  User: Кадет
  Date: 26.10.13
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" media="screen"
          href="/web-resources/bootstrap/css/bootstrap.min.css"/>

</head>
<body>


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

<div class="row">
    <div class="span3 well pagination-centered">
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


<%if (request.getAttribute("editSuccess") != null && (Boolean) request.getAttribute("editSuccess")) { %>
<div class="row">
    <div class="span 6  well control-group success pagination-centered">
        Обновление успешно!
    </div>
</div>
<%}%>

<div class="row">

    <div class="span6 pagination-centered well">
        <form action="/editChapter" method="POST" role="form">
            <%List<Chapter> chapters = (List<Chapter>) request.getAttribute("chapters");%>
            <%if (chapters != null) {%>

            <select name="editChapter.id" multiple class="form-control">
                <%for (Chapter chapter : chapters) {%>
                <option value="<%=chapter.getId()%>">
                    <%=chapter.getTitle()%>
                </option>
                <%}%>
            </select>

            <%}%>
            <br>
            <input type="text" name="editChapter.title" class="input-large" placeholder="Название главы"/>
            <br>
            <textarea name="editChapter.text" class="input-large" rows="20" placeholder="Текст"></textarea>
            <br>
            <input type="submit" value="<%=Messages.EDIT_CHAPTER_BUTTON_TEXT%>" class="btn btn-primary"/>
        </form>
    </div>

</div>

<div class="row">
    <div class="span6 pagination-centered well">
        <a href="/openChapter" class="btn btn-default">На главную</a>
    </div>
</div>

<%} else { %>

<div class="row">
    <div class="span 6 pagination-centered well control-group success">
        <%=Messages.NOT_REGISTERED%>
    </div>
</div>

<%}%>

</body>
</html>