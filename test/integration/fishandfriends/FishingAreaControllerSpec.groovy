package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingAreaControllerSpec extends IntegrationSpec {

    def fishingAreaController = new FishingAreaController()

    void "test ajout de note"() {
        given: "A user logged in"
        fishingAreaController.session.fishingMan = FishingMan.findById(1)
        fishingAreaController.params.fishingArea = 2
        fishingAreaController.params.note = "5"
        def n = Note.count()

        when: "The user put the note"
        fishingAreaController.note()

        then: "A note is added"
        Note.count == n + 1
        Note.where {
            if (fishingMan == fishingAreaController.session.fishingMan && fishingArea.id == 2) {
                it.delete(flush: true)
            }
        }
    }
}
