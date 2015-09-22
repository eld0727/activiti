define([
    "./Vote",
    "./data/VoteCollection",
    "backbonejs"
], function (Vote, VoteCollection, Backbone) {
    return Backbone.View.extend({

        tagName: "div",

        className: "",

        events: {},

        initialize: function() {
            this.collection = new VoteCollection();
            this.load();
        },

        load: function () {
            var self = this;
            this.collection.fetch({
                success: function (data) {
                    if(data.models.length === 0) {
                        self.showNoVotesMessage();
                    } else {
                        _.each(data.models, self.addVote, self);
                    }
                },
                error: function () {
                    console.log([].slice.call(arguments, 0));
                }
            });
        },

        addVote: function (model) {
            var vote = new Vote({model: model});
            this.$el.append(vote.render().el);
        },

        showNoVotesMessage: function () {
            this.$el.html("<h3>You are not participant of any vote.</h3>")
        }
    });
});
