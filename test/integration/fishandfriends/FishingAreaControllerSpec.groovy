package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingAreaControllerSpec extends IntegrationSpec {

    def fishingAreaController = new FishingAreaController()

    void "test show method" (){
        given: "A user logged"
        fishingAreaController.session.fishingMan = FishingMan.findById(1)

        when: "I want to see the fishingArea"
        fishingAreaController.show(FishingArea.findById(1))

        then: "I have the good values for the view"
        fishingAreaController.modelAndView.model.fishingAreaInstance == FishingArea.findById(1)
        fishingAreaController.modelAndView.model.catches.size == 1
        fishingAreaController.modelAndView.model.catches.get(0).aCatch.id == 3
        fishingAreaController.modelAndView.model.catches.get(0).nbComments == 2
        fishingAreaController.modelAndView.model.score.nbCatch == 1
        fishingAreaController.modelAndView.model.score.nbFishingMan == 3
        fishingAreaController.modelAndView.model.score.note == 3.5f
        fishingAreaController.modelAndView.model.isAlreadySuscribing == true
        fishingAreaController.modelAndView.model.noteGiven == 3
        fishingAreaController.modelAndView.viewName == '/fishingArea/show'
    }

    void "test create fishingArea"(){
        given: "A user logged and the form params to create a fishing Area"
        fishingAreaController.session.fishingMan = FishingMan.findById(1)
        fishingAreaController.params.fishingAreaLocation = "azertyuiop"
        fishingAreaController.params.fishingAreaName = "aqwzsxedc"
        fishingAreaController.params.fishingAreaDescription = "nbvcxwmlkjhgfdsq"

        when: "I submit the form"
        fishingAreaController.createFishingArea()
        def fishingAreaCreated = FishingArea.findByDescriptionAndNameAndLocation(fishingAreaController.params.fishingAreaDescription,fishingAreaController.params.fishingAreaName,fishingAreaController.params.fishingAreaLocation)

        then: "I'm redirected to the fishingArea page"
        fishingAreaController.response.redirectedUrl.equals("/fishingArea/show/"+fishingAreaCreated.id)

        fishingAreaCreated.delete(flush: true)
    }

    void "test add create area"(){
        when: "I want to create a fishingArea"
        fishingAreaController.addNewArea(FishingMan.findById(1))

        then: "I'm redirected to use create page"
        fishingAreaController.modelAndView.viewName == "/fishingArea/create"
        fishingAreaController.modelAndView.model.fishingManInstance == FishingMan.findById(1)
    }

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

    void "test unsubscribe"(){
        given: "A user logged"
        fishingAreaController.session.fishingMan = FishingMan.findById(1)

        when: "The user unsubscribe"
        fishingAreaController.suscribeUnsuscribeToArea(FishingArea.findById(1))

        then: "The user is not anymore subscribed and is redirected to the same page"
        !FishingArea.findById(1).fishingMen.contains(FishingMan.findById(1))
        fishingAreaController.response.redirectedUrl.equals("/fishingArea/show/1")
    }

    void "test subscribe" (){
        given: "A user logged"
        fishingAreaController.session.fishingMan = FishingMan.findById(1)

        when: "The user subscribe"
        fishingAreaController.suscribeUnsuscribeToArea(FishingArea.findById(2))

        then: "The user is not anymore subscribed and is redirected to the same page"
        FishingArea.findById(2).fishingMen.contains(FishingMan.findById(1))
        fishingAreaController.response.redirectedUrl.equals("/fishingArea/show/2")
    }
}
