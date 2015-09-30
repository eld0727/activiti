define([
    "../url",
    "backbonejs",
    "text!./template/CreateVoteForm.html",
    "jquery-validation-min",
    "noty"
], function (url, Backbone, template) {
    return Backbone.View.extend({

        tagName: "div",

        className: "container",

        events: {
            "click #add-option":    "addOption",
            "submit form":          "createVote"
        },

        num: 1,

        template: _.template(template),

        optTemplate: _.template(
                '<label for="option<%=num%>" class="col-sm-2 control-label">Option <%=num%></label>' +
                '<div class="col-sm-10">' +
                    '<input type="text" class="form-control" name="options" id="option<%=num%>" placeholder="Option <%=num%>">' +
                '</div>'
        ),

        userTemplate: _.template('<input type="checkbox" name="participants" value="<%-id%>"> <%-firstName%>'),

        initialize: function () {

        },

        render: function () {
            this.$el.html(this.template());
            this.configureUsers();
            return this;
        },

        configureUsers: function () {
            $.ajax({
                url: url("/api/users"),
                dataType: 'json',
                context: this,
                method: "GET",
                success: function (data) {
                    _.map(data, function (r) {
                        var input = $("<label >", {'class': 'checkbox-inline'});
                        input.html(this.userTemplate(r));
                        this.$("#participants").append(input);
                    }, this);

                }
            });
        },

        addOption: function () {
            var div = $("<div>", {'class': 'form-group'});
            div.html(this.optTemplate({num: ++this.num}));
            this.$("#opts-goes-before").before(div);
        },

        createVote: function (e) {
            var arr = $(e.target).serializeArray();
            var obj = {
                options: [],
                participants: []
            };
            _.each(arr, function (o) {
                if(!o.value) {
                    return;
                }
                var val = obj[o.name];
                if(val instanceof Array) {
                    val.push(o.value);
                } else {
                    obj[o.name] = o.value;
                }
            });
            $.ajax({
                url: url("/api/vote"),
                dataType: 'json',
                context: this,
                method: "PUT",
                data: JSON.stringify(obj),
                contentType: "application/json; charset=UTF-8",

                success: function (data) {
                    this.render();
                },
                error: function () {
                    noty({
                        text: "Something happens!",
                        type: "error",
                        layout: "topRight",
                        animation: {
                            open: {height: 'toggle'},
                            close: {height: 'toggle'},
                            easing: 'swing',
                            speed: 500
                        }
                    });
                }
            });
            return false;
        }
    });
});
