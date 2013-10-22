<%@ page import="com.kadet.HandBook.util.Messages" %>
<%@ page import="com.kadet.HandBook.entity.Chapter" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Кадет
  Date: 23.10.13
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>


Главная страница
<br>

<form action="/openChapter" method="GET">
    <%List<Chapter> chapters = (List<Chapter>) request.getAttribute("chapters");%>
    <%if (chapters != null) {%>

    <select name="chapterId" action="/openChapter" method="GET">
        <%for (Chapter chapter : chapters) {%>
        <option value="<%=chapter.getId()%>"><%=chapter.getTitle()%>
        </option>
        <%}%>
    </select>

    <%}%>


    <input type="submit" value="<%=Messages.OPEN_CHAPTER_BUTTON_TEXT%>"/>
</form>

<br>
<br>


<%String title = (String) request.getAttribute("title");%>
<%String text = (String) request.getAttribute("text");%>

<%if (title != null && text != null) {%>
<h2>
    <b>
        <%=title%>
    </b>
</h2>
<br>
<%=text%>
<%}%>


</body>
</html>