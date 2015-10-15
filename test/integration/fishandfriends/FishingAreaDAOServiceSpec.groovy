package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingAreaDAOServiceSpec extends IntegrationSpec {

    def fishingAreaDAOService = new FishingAreaDAOService()
    def fishingArea;



    def setup() {
        fishingArea = new FishingArea(location: "Toulouse", name: "Lac rose",description :"desc")
    }

    def cleanup() {
    }

    void "test comment creation"() {
        given: "a fishingArea"

        when: "save"
        def fishingAreaSaved = fishingAreaDAOService.saveFishingArea(fishingArea)

        then: "the instance is stored in db"
        fishingAreaSaved != null
        fishingAreaSaved.id != null
        fishingAreaSaved.name != null
        fishingAreaSaved.location != null

        fishingArea.delete()
    }

}
