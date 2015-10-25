package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingAreaServiceIntegrationSpec extends IntegrationSpec {

    def fishingManService = new FishingManService()
    def fishingAreaService = new FishingAreaService()
    FishingArea fishingArea,fishingArea1
    FishingMan fishingMan
    def fishingManList = []

    def setup() {
        fishingMan= new FishingMan(firstname: "JeanTest2", email: "jmtest2@yahoo.fr", tmpPassword: "password", lastname: "Duponttest2", gender: "H")
        fishingManService.insertOrUpdateFishingMan(fishingMan)
        fishingManList << fishingMan
        fishingArea = new FishingArea(location: "Toulouse", name: "Lac rose",description :"desc",fishingMen: fishingManList).save(flush: true)
        fishingArea1 = new FishingArea(location: "Zibabwe", name: "titicaca",description :"desc").save(flush: true)
    }

    def cleanup() {

        fishingArea.delete(flush : true)
        fishingMan.delete(flush : true)
        fishingArea1.delete(flush: true)
    }

    void "test isAlreadySuscribing"() {
        given: "an area and a fishingMan already suscribing to that area "


        when: "testing already suscribing"
        boolean result = fishingAreaService.isSuscriberToArea(fishingArea,fishingMan)
        then: "result is true"
        result

    }

    void "test !isAlreadySuscribing"(){
        given: "an area and a fishingMan not suscribing to that area"

        when: "testing already suscribing"
        boolean result = fishingAreaService.isSuscriberToArea(fishingArea1,fishingMan)
        then: "result is false"
        !result
    }
}
