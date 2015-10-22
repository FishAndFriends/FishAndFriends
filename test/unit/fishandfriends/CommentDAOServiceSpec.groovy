package fishandfriends

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * Test for the CommentDAOService.
 */
@TestFor(CommentDAOService)
@Mock([FishingMan, FishingArea, Comment])
class CommentDAOServiceSpec extends Specification {

    //def commentDAOService = new CommentDAOService()

    FishingMan fishingMan
    FishingArea fishingArea

    def setup() {
        fishingMan = new FishingMan(firstname:"Jean-Michel",email:"jzevnk@yahoo.fr", hashedPassword: "mypassword",saltedPassword:'passwordtest',lastname: "Dupont",gender: "H")
        fishingArea = new FishingArea(location: "zknvznv", name: "zonvzlnv", description: "desc")
        //fishingArea.save(flush: true)
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
        def commentSaved = service.saveComment(comment)

        then: "the instance is stored in db"
        commentSaved != null
        commentSaved.id != null
        commentSaved.dateCreated != null

        commentSaved.delete()
    }
}
