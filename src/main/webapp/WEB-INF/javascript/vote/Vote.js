define([
    "../url",
    "backbonejs",
    "text!./template/Vote.html"
], function (url, Backbone, template) {
    return Backbone.View.extend({

        tagName: "div",

        className: "col-md-3 col-sm-6",

        events: {
            "click input": "vote"
        },

        template: _.template(template),

        model: null,

        initialize: function() {
            this.listenTo(this.model, "fetch change", this.render);
        },

        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },

        vote: function (e) {
            this.$(".radio-group").hide();
            this.$(".well").append($("<div>", {'class': "loader"}));
            $.ajax({
                url: url("/api/vote"),
                method: "POST",
                context: this,
                data: JSON.stringify({option: $(e.target).val()}),
                contentType: "application/json; charset=UTF-8",
                success: function (data) {
                    this.model.set(data);
                }
            })
        }
    });
});
