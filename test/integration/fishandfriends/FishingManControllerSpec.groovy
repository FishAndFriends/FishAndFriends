package fishandfriends

import grails.test.spock.IntegrationSpec

class FishingManControllerSpec extends IntegrationSpec {

    def FishingManController fishingManController = new FishingManController()
    FishingManService fishingManService
    FishingMan fishingMan

    def setup() {
        fishingMan = FishingMan.findById(1)
        fishingManController.session.fishingMan = fishingMan
    }

//    def cleanup() {
//    }

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

    void "Test the edit view on someone else profile"() {
        when: "The edit action is executed"
        fishingManController.edit(FishingMan.findById(2))

        then: "The return view is correct"
        fishingManController.response.redirectedUrl.equals("/fishingMan/show/2")
    }

    void "Test the edit view with given arguments works well"() {
        given: "An error array and a fishing man"
        def errors = ['I will never use Grails in my entire life']
        fishingManController.flash.errors = errors
        fishingManController.flash.fishingMan = fishingMan

        when: "The edit action is executed"
        fishingManController.edit(fishingMan)

        then: "The model is correct"
        fishingManController.modelAndView.model.fishingManInstance == fishingMan
        fishingManController.modelAndView.model.errors == errors
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

    void "Test to edit the profile with incorrect infos"() {
        given:
        fishingManController.params.firstnameEdit = "toto"
        fishingManController.params.lastnameEdit = ""

        when: "The edit action is executed"
        fishingManController.editProfile(fishingMan)

        then: "The profile is edited"
        fishingManController.response.redirectedUrl.equals("/fishingMan/edit/1")
    }

    void "Test to edit the profile of somebody else"() {
        given:
        fishingManController.params.firstnameEdit = "grails"
        fishingManController.params.lastnameEdit = "isBad"

        def fishingManTest = FishingMan.findById(2)
        def oldFirstname = fishingManTest.firstname
        def oldLastname = fishingManTest.lastname

        when: "The edit action is executed"
        fishingManController.editProfile(fishingManTest)

        then: "The profile is edited"
        fishingManController.response.redirectedUrl.equals("/fishingMan/show/2")
        FishingMan.findById(2).firstname == oldFirstname
        FishingMan.findById(2).lastname == oldLastname
    }


    void "Test to edit the password with a wrong old password"() {
        given:
        fishingManController.params.oldPassword = "notTheGoodOne"
        fishingManController.params.newPassword = "jAimePasLesTests"

        when: "The edit action is executed"
        fishingManController.editPassword(fishingMan)

        then: "The password didn't changed"
        fishingManService.controlPassword(FishingMan.findById(1), "notTheGoodOne") == false
    }

    void "Test to edit the password with a good old password"() {
        given:
        fishingManController.params.oldPassword = "password"
        fishingManController.params.newPassword = "jAimePasLesTests"

        when: "The edit action is executed"
        fishingManController.editPassword(fishingMan)

        then: "The password changed"
        fishingManController.response.redirectedUrl.equals("/fishingMan/show/1")
        fishingManService.controlPassword(FishingMan.findById(1), "jAimePasLesTests") == true
    }

    void "Test to edit the password with an invalid new password"() {
        given:
        fishingManController.params.oldPassword = "password"
        fishingManController.params.newPassword = "starWarsIsComing"

        def fishingManTest = FishingMan.findById(2)

        when: "The edit action is executed"
        fishingManController.editPassword(fishingManTest)

        then: "The password didn't changed"
        fishingManController.response.redirectedUrl.equals("/fishingMan/show/2")
        fishingManService.controlPassword(FishingMan.findById(2), "starWarsIsComing") == false
    }

    void "Test to edit the password of somebody else"() {
        given:
        fishingManController.params.oldPassword = "password"
        fishingManController.params.newPassword = "nope"

        when: "The edit action is executed"
        fishingManController.editPassword(fishingMan)

        then: "The password changed"
        fishingManController.response.redirectedUrl.equals("/fishingMan/edit/1")
        fishingManService.controlPassword(FishingMan.findById(1), "nope") == false
    }
}
