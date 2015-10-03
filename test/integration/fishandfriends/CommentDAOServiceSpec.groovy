package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * Test for the CommentDAOService.
 */
class CommentDAOServiceSpec extends IntegrationSpec {

    def commentDAOService = new CommentDAOService()

    FishingMan fishingMan
    FishingArea fishingArea

    def setup() {
        fishingMan = new FishingMan(firstname:"Jean-Michel",email:"jm@yahoo.fr",password: "mypassword",lastname: "Dupont",gender: "H")
        fishingArea = new FishingArea(location: "zknvznv", name: "zonvzlnv")
        fishingArea.save(flush: true)
        //fishingMan.save(flush: true)
    }

    def cleanup() {
    }

    void "test comment creation"() {
        given: "a comment"
        Comment comment = new Comment()
        comment.text = "text"
        comment.fishingMan = fishingMan
        comment.commentable = fishingArea

        when: "save"
        comment = commentDAOService.saveComment(comment)

        then: "the instance is stored in db"
        comment.id != null
        comment.dateCreated != null
    }
}
