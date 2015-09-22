define([
    "backbonejs"
], function (Backbone) {
    return Backbone.Model.extend({
        defaults: {
            id: null,
            text: null,
            owner: null,
            options: [],
            participants: []
        }
    });
});
