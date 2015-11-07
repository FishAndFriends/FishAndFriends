package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class CatchServiceSpec extends IntegrationSpec {

    def catchService = new CatchService()

//    def setup() {
//        See bootstrap
//    }
//
//    def cleanup() {
//        See bootstrap
//    }

    void "test the research of a catch by fishingMan"() {
        given: "a fishingMan"
        FishingMan fishingman = FishingMan.findById(1)

        when:"searching for  catches"
        def result = catchService.getCatchesByFishingMan(fishingman)

        then:"the result is correct"
        result.size() == 1
        result.contains(Catch.findById(3))
    }


    void "test the research of catches by fishingMan"() {
        given: "a fishingMan"
        FishingMan fishingman = FishingMan.findById(2)

        when:"searching for  catches"
        def result = catchService.getCatchesByFishingMan(fishingman)

        then:"the result is correct"
        result.size() == 2

    }

    void "test get Catches With NbComments By FishingArea with catches on it and comment on them"() {
        when:"I want all catches with the number of comments"
        def result = catchService.getCatchesWithNbCommentsByFishingArea(FishingArea.findById(1))

        then:"I get 1 catch with 2 comments"
        result != null
        result.size() == 1
        result[0].nbComments == 2
    }

    void "test get Catches With NbComments By FishingArea with catches on it but without comments on them"() {
        when:"I want all catches with the number of comments"
        def result = catchService.getCatchesWithNbCommentsByFishingArea(FishingArea.findById(2))

        then:"I get 2 catch with 0 comments on them"
        result != null
        result.size() == 2
        result[0].nbComments == 0
        result[1].nbComments == 0
    }
}
