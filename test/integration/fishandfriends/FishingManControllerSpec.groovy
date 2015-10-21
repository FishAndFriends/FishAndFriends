package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingManControllerSpec extends IntegrationSpec {

    def FishingManController fishingManController = new FishingManController()
    FishingManService fishingManService
    FishingMan fishingMan
    def setup() {
        fishingMan = FishingMan.findById(1)
    }

    def cleanup() {
    }

    void "Test the show view works well"() {
        when: "The index action is executed"
        fishingManController.show(fishingMan)

        then: "The model is correct"
        fishingManController.modelAndView.model.fishingManInstance == fishingMan
    }

    void "Test the edit view works well"() {
        when: "The edit action is executed"
        fishingManController.edit(fishingMan)

        then: "The model is correct"
        fishingManController.modelAndView.model.fishingManInstance == fishingMan
    }

    void "Test to edit the profile"() {
        given:
        fishingManController.params.firstnameEdit = "toto"
        fishingManController.params.lastnameEdit = "tata"

        when: "The edit action is executed"
        fishingManController.editProfile(fishingMan)

        then: "The profile is edited"
        fishingManController.response.redirectedUrl.equals("/fishingMan/show/1")
        FishingMan.findById(1).firstname.equals("toto")
        FishingMan.findById(1).lastname.equals("tata")
    }


    void "Test to edit the password with a wrong password"() {
        given:
        fishingManController.params.oldPassword = "notTheGoodOne"
        fishingManController.params.newPassword = "jAimePasLesTests"

        when: "The edit action is executed"
        fishingManController.editPassword(fishingMan)

        then: "The password didn't changed"
        fishingManController.modelAndView.model.fishingManInstance == fishingMan
        fishingManService.controlPassword(FishingMan.findById(1), "notTheGoodOne") == false
    }



    void "Test to edit the password with a good password"() {
        given:
        fishingManController.params.oldPassword = "password"
        fishingManController.params.newPassword = "jAimePasLesTests"

        when: "The edit action is executed"
        fishingManController.editPassword(fishingMan)

        then: "The password changed"
        fishingManController.modelAndView.model.fishingManInstance == fishingMan
        fishingManService.controlPassword(FishingMan.findById(1), "jAimePasLesTests") == true
    }
}
