package fishandfriends

import grails.test.spock.IntegrationSpec

class CommentControllerSpec extends IntegrationSpec {

    FishingArea fishingArea
    FishingMan fishingMan
    Comment comment
    def commentController = new CommentController()

    def setup() {
        fishingArea = new FishingArea(location: "zkdv", name: "eknvenve", description: "desc")
        fishingMan = new FishingMan(firstname: 'azerty', lastname: 'azertry', email: 'azert@azert.fr', hashedPassword: 'qksjnfl',saltedPassword:'zjhduzudhe', gender: 'H')
        fishingArea.save(flush: true)
        fishingMan.save(flush: true)
        comment = new Comment(text: "edvk,kv,", fishingMan: fishingMan, commentable: fishingArea)
        comment.save(flush: true)
    }

    def cleanup(){
        comment.delete(flush: true)
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
     */
    void "Test show all comments works well"() {
        given:
        commentController.params.commentable = fishingArea

        when: "The index action is executed"
        commentController.showAllComment()

        then: "The the comment is created"
        commentController.response.text.contains(comment.text)
        commentController.response.text.contains(comment.fishingMan.firstname)
        commentController.response.text.contains(comment.fishingMan.lastname)
    }

    /**
     * This test will just make sure that params are good.
     * For the behavior, refer to CommentService!
     *
     */
    void "Test show 5 comments works well"() {
        given:
        commentController.params.commentable = fishingArea

        when: "The index action is executed"
        commentController.show5Comments()

        then: "The the comment is created"
        commentController.response.text.contains(comment.text)
        commentController.response.text.contains(comment.fishingMan.firstname)
        commentController.response.text.contains(comment.fishingMan.lastname)
    }
}
