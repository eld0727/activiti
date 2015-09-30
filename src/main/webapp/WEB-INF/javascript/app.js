define([
    "./url",
    "require",
    "backbonejs",
    "jquery",
    "noty"
], function (url, require, Backbone) {
    var f = function(text) {
        noty({
            text: text,
            animation: {
                open: {height: 'toggle'},
                close: {height: 'toggle'},
                easing: 'swing',
                speed: 500
            }
        });
    };

    var Router = Backbone.Router.extend({
        routes: {
            "":                 "vote",
            "vote":             "vote",
            "vote/create":      "createVote",
            "noty":             "notifications",
            "edit":             "editProcess"
        },

        /**
         * Current state of the router
         */
        state: null,

        vote: function() {
            this.state = "vote";
            var self = this;
            require(["./vote/VoteList"], function (VoteList) {
                if(self.state !== "vote") {
                    return;
                }
                var voteList = new VoteList();
                $("#main-container")
                    .empty()
                    .append(voteList.render().el);
            });
        },

        createVote: function () {
            this.state = "createVote";
            var self = this;
            require(["./vote/CreateVoteForm"], function (VoteList) {
                if(self.state !== "createVote") {
                    return;
                }
                var voteList = new VoteList();
                $("#main-container")
                    .empty()
                    .append(voteList.render().el);
            });
        },

        editProcess: function () {
            this.state = "editProcess";
            var self = this;
            require(["./process/ProcessEditor"], function (ProcessEditor) {
                if(self.state !== "editProcess") {
                    return;
                }
                var editor = new ProcessEditor();
                $("#main-container")
                    .empty()
                    .append(editor.render().el);
            });
        }
    });

    var attempts = 5;
    var timeout = 1000;
    var notificationsCheck = function () {
        $.ajax({
            url: url("/api/notification/undelivered"),
            success: function (data) {
                if(data instanceof Array && data.length > 0) {
                    _.each(data, function (n) {
                        noty({
                            text: n.data,
                            layout: "topRight",
                            animation: {
                                open: {height: 'toggle'},
                                close: {height: 'toggle'},
                                easing: 'swing',
                                speed: 500
                            }
                        });
                    });
                }
                attempts = 5;
                setTimeout(notificationsCheck, timeout);
            },
            error: function () {
                if(--attempts) {
                    setTimeout(notificationsCheck, timeout);
                }
            }
        });
    };

    notificationsCheck();

    new Router();
    Backbone.history.start({pushState: false, root: window._contextPath + "/"});

});