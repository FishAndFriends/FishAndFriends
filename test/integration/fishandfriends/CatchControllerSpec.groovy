package fishandfriends

import grails.test.spock.IntegrationSpec

class CatchControllerSpec extends IntegrationSpec {
    def CatchController controller = new CatchController()
    FishingMan fishingMan

    def setup() {
        fishingMan = FishingMan.findById(1)
        controller.session.fishingMan = fishingMan
    }

    void "Test the share view works well"() {
        when: "The share action is executed"
        controller.share()

        then: "The model is correct"
        controller.modelAndView.model.catchInstance.id == null
    }

    void "Test the share from location view works well"() {
        given: "A fishing area"
        FishingArea fishingArea = FishingArea.findById(1)

        when: "The share action is executed"
        controller.shareFromArea(fishingArea)

        then: "The model is correct"
        controller.response.redirectedUrl.startsWith("/catch/share")
    }

    void "Test to save an invalid catch"() {
        given: "An invalid new catch"
        Catch aCatch = new Catch(fishingArea: FishingArea.findById(1))

        when: "The share action is executed"
        controller.save(aCatch)

        then: "The model is correct"
        controller.response.redirectedUrl.startsWith("/catch/share")
    }

    void "Test to save a valid catch"() {
        given: "An valid new catch"
        Catch aCatch = new Catch()
        aCatch.fish = Fish.findById(1)
        aCatch.fishingArea = FishingArea.findById(1)
        aCatch.size = 1f
        aCatch.weight = 1f

        when: "The share action is executed"
        controller.save(aCatch)

        then: "The model is correct"
        controller.response.redirectedUrl.startsWith("/")
    }
}
