package fishandfriends

import grails.test.spock.IntegrationSpec
import spock.lang.Unroll


class FishingAreaServiceSpec extends IntegrationSpec {

    def fishingAreaService = new FishingAreaService()
    FishingArea fishingArea,fishingArea2,fishingArea3,fishingArea4

    def setup() {
        fishingArea = new FishingArea(location: "Paris", name: "Lac rose").save(flush: true)
        fishingArea2 = new FishingArea(location: "Montauban", name: "Lac des plaines").save(flush: true)
        fishingArea3 = new FishingArea(location: "Perigueux", name: "Lac des places").save(flush: true)
        fishingArea4 = new FishingArea(location: "Montauban", name: "super etang").save(flush: true)
    }

    def cleanup() {
        fishingArea.delete(flush: true)
        fishingArea2.delete(flush: true)
        fishingArea3.delete(flush: true)
        fishingArea4.delete(flush: true)
    }

    void "test research of fishingArea"() {
        when:"searching for multiple fishingArea"
        def result = fishingAreaService.search(5,0,"Lac")

        then:"the result is correct"
        result.size() == 3
        result.contains(fishingArea)
        result.contains(fishingArea2)
        result.contains(fishingArea3)
        !result.contains(fishingArea4)
    }

    void "test research of one fishingArea instead of 3"() {
        when:"searching for 2 out of 5 fishingArea"
        def result2 = fishingAreaService.search(5,0,"mon")

        then:"the result is correct"
        result2.size() == 2
        !result2.contains(fishingArea)
        result2.contains(fishingArea2)
        !result2.contains(fishingArea3)
        result2.contains(fishingArea4)
    }

    void "test research of one fishingArea instead of 2"() {
        when:"searching for multiple fishingArea"
        def result3 = fishingAreaService.search(5,0,"Paris")

        then:"the result is correct"
        result3.size() == 1
        result3.contains(fishingArea)
        !result3.contains(fishingArea2)
        !result3.contains(fishingArea3)
        !result3.contains(fishingArea4)
    }
}