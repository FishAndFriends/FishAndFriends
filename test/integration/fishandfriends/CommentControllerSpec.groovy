package fishandfriends

import grails.test.spock.IntegrationSpec

class CommentControllerSpec extends IntegrationSpec {

    FishingArea fishingArea
    FishingMan fishingMan
    Comment comment
    def commentController = new CommentController()

    def setup() {
        fishingArea = new FishingArea(location: "zkdv", name: "eknvenve")
        fishingMan = new FishingMan(firstname: 'azerty', lastname: 'azertry', email: 'azert@azert.fr', hashedPassword: 'qksjnfl', gender: 'H')
        fishingArea.save(flush: true)
        fishingMan.save(flush: true)
        comment = new Comment(text: "edvk,kv,", fishingMan: fishingMan, fishingArea: fishingArea)
        comment.save(flush: true)
    }

    void "Test the index action works well"() {
        given:
        commentController.params.commentable = fishingArea
        commentController.params.fishingMan = fishingMan

        when: "The index action is executed"
        commentController.index()

        then: "The model is correct"
        commentController.modelAndView.model.fishingMan == fishingMan
        commentController.modelAndView.model.commentable == fishingArea
    }

    void "Test create comment works well"() {
        given:
        int nb = Comment.count()
        commentController.params."commentable.id" = fishingArea.id
        commentController.params."fishingMan.id" = fishingMan.id
        commentController.params.text = "azerty"

        when: "The index action is executed"
        commentController.createComment()

        then: "The the comment is created"
        nb + 1 == Comment.count()
    }

    /**
     * This test will just make sure that params are good.
     * For the behavior, refer to CommentService!
     *
     * TODO: try to improve the behavior of this test !
     */
    void "Test show all comments works well"() {
        given:
        commentController.params.commentable = fishingArea
        def result = commentController.render(template: "showAllComments", model: [comments: [comment]]).toString()

        when: "The index action is executed"
        commentController.showAllComment()

        then: "The the comment is created"
        commentController.response.text.contains(comment.text)
        commentController.response.text.contains(comment.fishingMan.firstname)
        commentController.response.text.contains(comment.fishingMan.lastname)
    }
}
