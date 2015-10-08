package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Comment)
class CommentSpec extends Specification {

    Comment comment
    @Shared FishingArea fishingArea
    @Shared FishingMan fishingMan
    def setup() {
        fishingMan = new FishingMan(firstname: 'azerty', lastname: 'azertry', email: 'azert@azert.fr', hashedPassword: 'qksjnfl', gender: 'H')
        fishingArea = new FishingArea(name: 'azetr', location: 'loc')
        comment = new Comment()
    }

    def cleanup() {
    }

    void "test validate"() {
        given:"a comment"
        comment.fishingMan = fishingMan
        comment.commentable = fishingArea
        comment.text = "text"

        when:"validating the fishingman"
        def isValid = comment.validate()

        then:"the fishingman is valid"
        isValid == true
    }

    void "test invalidate"() {
        given:"a comment"
        comment.fishingMan = fishingManTest
        comment.commentable = commentableTest
        comment.text = textTest

        when:"validating the fishingman"
        def isValid = comment.validate()

        then:"the fishingman is valid"
        isValid == false

        where:
        fishingManTest      | commentableTest      |  textTest
        null                | fishingArea          |  "text"
        null                | fishingArea          |  ""
        fishingMan          | fishingArea          |  null
        fishingMan          | null                 |  "text"
    }
}
