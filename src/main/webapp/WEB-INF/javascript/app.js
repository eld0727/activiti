define([
    "jquery",
    "backbonejs",
    "underscorejs",
    "noty"
], function ($, backbone, _) {
    noty({
        text: 'Hello RequireJS with noty!',
        animation: {
            open: {height: 'toggle'},
            close: {height: 'toggle'},
            easing: 'swing',
            speed: 500
        }
    });
});