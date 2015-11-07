package fishandfriends

import grails.test.spock.IntegrationSpec
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
class LoginControllerSpec extends IntegrationSpec {

    def controller = new LoginController()

    void "test redirection newsfeed logged user"(){
        given: "A user logged"
        controller.session.fishingMan = FishingMan.findById(1)

        when: "I want to go to the newsfeed"
        controller.index()

        then: "I'm redirected to the newsfeed"
        controller.modelAndView.viewName == "/login/newsfeed"
    }

    void "test redirection newsfeed not logged"() {
        given: "A user not logged"
        controller.session.fishingMan = null

        when: "I want to the newsfeed"
        controller.index()

        then: "I'm redirected to the login page"
        controller.modelAndView.viewName == '/login/index'
    }

    void "test redirection newsfeed not logged after bad sign up"() {
        given: "A user not logged after a bad inscription try"
        controller.session.fishingMan = null
        controller.inscription()

        when: "I want to the newsfeed"
        controller.index()

        then: "I'm redirected to the login page with errors"
        controller.modelAndView.viewName == '/login/index'
        controller.modelAndView.model.fishingManInstance != null
        controller.modelAndView.model.errors == null
    }

    void "test redirection newsfeed not logged after bad log in"() {
        given: "A user not logged after a bad inscription try"
        controller.session.fishingMan = null
        controller.connexion()

        when: "I want to the newsfeed"
        controller.index()

        then: "I'm redirected to the login page with errors"
        controller.modelAndView.viewName == '/login/index'
        controller.modelAndView.model.fishingManInstance == null
        controller.modelAndView.model.errors != null
    }

    void "test connexion for a valid user"() {
        given: "a form complete with good values"
        controller.params.connectMail = FishingMan.findById(1).email
        controller.params.connectPwd = "password"

        when: "I log in"
        controller.connexion()

        then: "No error"
        controller.flash.errors == []
        controller.response.redirectedUrl == "/"
    }

    void "test connexion for an invalid user"() {
        given: "a form complete with bad values"
        controller.params.connectMail = "zojbzbvozknzknknjr@zjbbjiz.fr"
        controller.params.connectPwd = "password"

        when: "I log in"
        controller.connexion()

        then: "Errors"
        controller.flash.errors != null
        controller.response.redirectedUrl == "/"
    }

    void "test connexion with email params forgotten"(){
        given: "a login form incomplete"
        controller.params.connectPwd = "password"

        when: "I log in"
        controller.connexion()

        then: "Errors"
        controller.flash.errors != null
        controller.flash.errors.size() == 1
        controller.response.redirectedUrl == "/"
    }

    void "test connexion with password params forgotten"(){
        given: "a login form incomplete"
        controller.params.connectMail = "zojbzbvozknzknknjr@zjbbjiz.fr"

        when: "I log in"
        controller.connexion()

        then: "Errors"
        controller.flash.errors != null
        controller.flash.errors.size() == 1
        controller.response.redirectedUrl == "/"
    }

    void "test disconnection of the user"() {
        when: "user is already disconnected"
        controller.deconnexion()

        then: "user is redirected to connexion menu"
        controller.response.redirectUrl == '/'
    }

    void "test inscription OK"(){
        given: "A sign in form completed"
        controller.params.signupFirstname = "AZzreknv"
        controller.params.signupLastname = "ajknvbzlknvzlkv"
        controller.params.signupMail = "qkbvsj@zjkb.fr"
        controller.params.radioGender = "F"
        controller.params.signupPwd = "azertyuio"
        def n = FishingMan.count()

        when: "I sign in"
        controller.inscription()

        then: "A user is saved"
        FishingMan.count() == n + 1
        controller.session.fishingMan.id == FishingMan.findByEmail(controller.params.signupMail).id
        controller.response.redirectUrl == '/'

        FishingMan.findByEmail(controller.params.signupMail).delete(flush: true)
    }

    void "test inscription KO"(){
        given: "A sign in form completed"
        controller.params.signupFirstname = "AZzreknv"
        controller.params.signupLastname = "ajknvbzlknvzlkv"
        controller.params.radioGender = "F"
        controller.params.signupPwd = "azertyuio"
        def n = FishingMan.count()

        when: "I sign in"
        controller.inscription()

        then: "A user is saved"
        FishingMan.count() == n
        controller.session.fishingMan == null
        controller.response.redirectUrl == '/'
    }
}
