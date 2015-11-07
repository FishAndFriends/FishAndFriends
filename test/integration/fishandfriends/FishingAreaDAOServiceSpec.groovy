package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingAreaDAOServiceSpec extends IntegrationSpec {

    def fishingAreaDAOService = new FishingAreaDAOService()
    def fishingManService = new FishingManService()
    FishingArea fishingArea , fishingArea2, fishingArea3
    FishingMan fishingMan
    def fishingMenList = []

    def setup() {
        fishingMan= new FishingMan(firstname: "JeanTest", email: "jmtest@yahoo.fr", tmpPassword: "password", lastname: "Duponttest", gender: "H")
        fishingMenList << fishingMan
        fishingArea = new FishingArea(location: "Toulouse", name: "Lac rose",description :"desc")
        fishingArea2 = new FishingArea(location: "Lille", name: "rivière",description :"frf").save(flush: true)


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


    void "test suscribe a fishingMan"() {

        given: "a fishingMan and a fishingArea"
        //FishingMan fishingMan = fishingManService.insertOrUpdateFishingMan(new FishingMan(firstname: "Jean-test", email: "jmtest@yahoo.fr", tmpPassword: "password", lastname: "Duponttest", gender: "H"))
        fishingManService.insertOrUpdateFishingMan(fishingMan)

        when: "suscribing a fishingMan"
        fishingAreaDAOService.suscribeUnsuscribeToArea(fishingArea2,fishingMan)

        then: "the fishingMan is a suscriber of the area"
        //def result = fishingArea.fishingMen.contains(FishingMan.findById(1))
        def fishingManList = fishingArea2.getFishingMen()
        def result =  fishingManList.size()
        result == 1
        fishingMan.delete()

    }

    void " test unsuscribing a fishingMan"() {

        given: "a fishingMan and a fishingArea"
        fishingManService.insertOrUpdateFishingMan(fishingMan)
        fishingArea3 = new FishingArea(location: "marseilles", name: "panam",fishingMen : fishingMenList,description: "tres beau coin").save(flush:true)

        when: "unsuscribing a fishingMan from an area"
        //unsuscribe
        fishingAreaDAOService.suscribeUnsuscribeToArea(fishingArea3,fishingMan)

        then: "the fishingMan is not in the list anymore"
        def fishingManList = fishingArea3.getFishingMen()
        def result = fishingManList.size()
        result == 0
        fishingMan.delete()
    }




}
