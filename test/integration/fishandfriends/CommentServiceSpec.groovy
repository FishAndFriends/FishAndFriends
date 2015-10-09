package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * This test should be an unit test.
 * BUT : Grails criteria on abstract properties doesn't work with unit test.
 */
class CommentServiceSpec extends IntegrationSpec {

    def commentService = new CommentService()

    FishingMan fishingMan
    FishingArea fishingArea
    FishingArea fishingArea1

    def setup() {
        fishingMan = new FishingMan(firstname: 'azerty', lastname: 'azertry', email: 'azert@azert.fr', hashedPassword: 'qksjnfl', saltedPassword: 'zjhdduzudhe', gender: 'H')
        fishingArea = new FishingArea(name: 'azkrgzetr', location: 'legeegeoc')
        fishingArea1 = new FishingArea(name: 'sdzgrekv,dv', location: 'loehegrc')

        fishingMan.save(flush: true)
        fishingArea.save(flush: true)
        fishingArea1.save(flush: true)
    }

    def cleanup() {
        fishingMan.delete(flush: true)
        fishingArea.delete(flush: true)
        fishingArea1.delete(flush: true)
    }

    void "test retrieve all comments for a fishing area"() {
        given:
        Comment comment = new Comment(fishingMan: fishingMan, commentable: fishingArea, text: "zovker")
        Comment comment1 = new Comment(fishingMan: fishingMan, commentable: fishingArea, text: "sdlkv,edokv,")
        Comment comment2 = new Comment(fishingMan: fishingMan, commentable: fishingArea1, text: "sdlkv,edokv,")
        comment.save(flush: true)
        comment1.save(flush: true)
        comment2.save(flush: true)

        when:
        def result = commentService.getAllCommentsForCommentable(fishingArea)

        then:
        result.size() == 2
        result.contains(comment)
        result.contains(comment1)
        !result.contains(comment2)

        comment.delete()
        comment1.delete()
        comment2.delete()
    }
}
