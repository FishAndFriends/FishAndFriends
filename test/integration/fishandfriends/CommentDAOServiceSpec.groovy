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
        fishingMan = new FishingMan(firstname:"Jean-Michel",email:"jzevnk@yahoo.fr", hashedPassword: "mypassword",saltedPassword:'passwordtest',lastname: "Dupont",gender: "H")
        fishingArea = new FishingArea(location: "zknvznv", name: "zonvzlnv", description: "desc")
        fishingArea.save(flush: true)
        //fishingMan.save(flush: true)
    }

    def cleanup() {
        fishingMan.delete()
        fishingArea.delete()
    }

    void "test comment creation"() {
        given: "a comment"
        Comment comment = new Comment()
        comment.text = "text"
        comment.fishingMan = fishingMan
        comment.commentable = fishingArea

        when: "save"
        def commentSaved = commentDAOService.saveComment(comment)

        then: "the instance is stored in db"
        commentSaved != null
        commentSaved.id != null
        commentSaved.dateCreated != null

        commentSaved.delete()
    }
}
