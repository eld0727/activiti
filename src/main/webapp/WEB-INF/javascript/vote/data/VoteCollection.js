define([
    "../../url",
    "./Vote",
    "backbonejs"
], function (url, Vote, Backbone) {
    return Backbone.Collection.extend({
        model: Vote,
        url: url("/api/vote")
    });
});
