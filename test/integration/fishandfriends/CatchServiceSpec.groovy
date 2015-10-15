package fishandfriends

import grails.test.spock.IntegrationSpec
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class CatchServiceSpec extends IntegrationSpec {

    def catchService = new CatchService()

    def setup() {
    }

    def cleanup() {
    }

    void "test the research of a catch by fishingMan"() {
        given: "a fishingMan"
        FishingMan fishingman = FishingMan.findById(1)

        when:"searching for  catches"
        def result = catchService.getCatchesByFishingMan(fishingman)

        then:"the result is correct"
        result.size() == 1
        result.contains(Catch.findById(1))
    }


    void "test the research of catches by fishingMan"() {
        given: "a fishingMan"
        FishingMan fishingman = FishingMan.findById(2)

        when:"searching for  catches"
        def result = catchService.getCatchesByFishingMan(fishingman)

        then:"the result is correct"
        result.size() == 2

    }

    void "test the research of a catch by fishingArea"() {
        given: " a fishingArea"
        FishingArea fishingArea = FishingArea.findById(1)

        when:"searching for catches"
        def result = catchService.getCatchesByFishingArea(fishingArea)

        then:"the result is correct"
        result.size() == 1
    }


    void "test the research of catches by fishingArea"() {
        given: " a fishingArea"
        FishingArea fishingArea = FishingArea.findById(2)

        when:"searching for catches"
        def result = catchService.getCatchesByFishingArea(fishingArea)

        then:"the result is correct"
        result.size() == 2
    }

    void "test a false research of a catch by fishingArea"() {
        given: " a fishingArea"
        FishingArea fishingArea = new FishingArea(name: "nom",location: "loc",description: "desc")

        when:"searching for catches"
        def result = catchService.getCatchesByFishingArea(fishingArea)

        then:"the result is correct"
        result.size() == 0
    }


}
