<%@ page import="org.webjars.WebJarAssetLocator" %>
<%--
  Created by alex on 21.09.2015.
  index file
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% WebJarAssetLocator webJarAssetLocator = new WebJarAssetLocator(); %>
    <title>notifications</title>
    <script src="/webjarsjs"></script>
    <link rel='stylesheet' href='<%= webJarAssetLocator.getFullPath("css/bootstrap.min.css") %>'>
    <script data-main="/js/app" src="<%= webJarAssetLocator.getFullPath("require.min.js") %>"></script>
</head>
<body>

</body>
</html>
