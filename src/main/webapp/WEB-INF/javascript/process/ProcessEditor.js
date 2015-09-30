define([
    "text!./template/ProcessEditor.html",
    "backbonejs"
], function (template, Backbone) {
    return Backbone.View.extend({

        tagName: "div",

        className: "process-editor container-fluid",

        events: {},

        initialize: function() {
        },

        template: _.template(template),

        render: function () {
            this.$el.html(this.template());
            return this;
        }
    });
});
