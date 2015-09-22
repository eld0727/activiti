define([], function () {
    return function(url) {
        if(url == null) {
            url = "/"
        } else if(url[0] !== "/") {
            url = "/" + url;
        }
        return window._contextPath + url;
    }
});