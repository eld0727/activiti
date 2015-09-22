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
    <link rel='stylesheet' href='<%= webJarAssetLocator.getFullPath("css/bootstrap-multiselect.css").replace("META-INF/resources/", "") %>'>
    <script src="<%= webJarAssetLocator.getFullPath("require.min.js").replace("META-INF/resources/", "") %>"></script>
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">activiti vote</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#vote">Available votes</a></li>
                    <li><a href="#vote/create">Create vote</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="<%=request.getContextPath()%>/logout">logout</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="starter-template" id="main-container">
        </div>
    </div>

    <script>
        window._contextPath = "<%=request.getContextPath()%>";
        require(["js/app"]);
    </script>

</body>
</html>
