<%@ page import="org.webjars.WebJarAssetLocator" %>
<%--
  Created by alex on 21.09.2015.
  login file
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% WebJarAssetLocator webJarAssetLocator = new WebJarAssetLocator(); %>
    <title>Login</title>
    <link rel='stylesheet' href='<%= webJarAssetLocator.getFullPath("css/bootstrap.min.css").replace("META-INF/resources/", "") %>'>
    <link rel='stylesheet' href='<%=request.getContextPath()%>/style/login.css'>
    <script data-main="/js/app" src="<%= webJarAssetLocator.getFullPath("jquery.min.js").replace("META-INF/resources/", "") %>"></script>
</head>
<body>
    <div class="container">
        <form class="form-signin" action="<%=request.getContextPath()%>/login" method="POST">
            <h2 class="form-signin-heading">Пожалуйста войдите</h2>
            <label for="name" class="sr-only">Логин</label>
            <input type="text" name="username" id="name" class="form-control" placeholder="Логин" required autofocus>
            <label for="inputPassword" class="sr-only">Пароль</label>
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Пароль" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </form>
    </div>
</body>
</html>
