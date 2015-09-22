<%@ page import="org.webjars.WebJarAssetLocator" %>
<%--
  Created by alex on 21.09.2015.
  index file
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% WebJarAssetLocator webJarAssetLocator = new WebJarAssetLocator(); %>
    <title>activiti noty</title>
    <link rel='stylesheet' href='<%=request.getContextPath()%>/style/index.css'>
    <script src="<%=request.getContextPath()%>/webjarsjs"></script>
    <link rel='stylesheet' href='<%= webJarAssetLocator.getFullPath("css/bootstrap.min.css").replace("META-INF/resources/", "") %>'>
    <script src="<%= webJarAssetLocator.getFullPath("require.min.js").replace("META-INF/resources/", "") %>"></script>
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">activiti noty</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#available">Available</a></li>
                    <li><a href="#about">Create</a></li>
                    <li><a href="#contact">Notifications</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container">

        <div class="starter-template">
            <h1>Bootstrap starter template</h1>
            <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a mostly barebones HTML document.</p>
        </div>

    </div>

    <script>
        require(["js/app"]);
    </script>

</body>
</html>
