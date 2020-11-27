<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Регистрация</title>
</head>

<body>
<div>
  <h2>Регистрация</h2>
  <form:form method="POST" modelAttribute="userForm">
    
    <div>
      <input type="text" value="login" id="login">


    </div>

    <button type="submit">Зарегистрироваться</button>
  </form:form>
  <a href="/">Главная</a>
</div>
</body>
</html>