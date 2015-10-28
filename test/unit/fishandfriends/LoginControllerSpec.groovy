package fishandfriends

import spock.lang.Specification
import grails.test.mixin.TestFor
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
class LoginControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["firstname"] = "toto"
        params["lastname"] = "titi"
        params["email"] = "toto@toto.com"
        params["hashedPassword"] = "tototo42"
        params["saltedPassword"] = "tototo42"
        params["gender"] = "H"
        params["tmpPassword"] = "tototo42"
    }

    void "test redirection newsfeed logged user"(){
        given: "A user logged"
        populateValidParams(params)
        controller.session.fishingMan = new FishingMan(params)

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
        controller.modelAndView.viewName == "/login/index"
    }

    void "test connexion for a valid user"() {
        given: "user connexion information"
        def paramsConnexion = [:]
        paramsConnexion["email"] = "toto@toto.com"
        paramsConnexion['hashedPassword'] = "tototo42"

        FishingMan user = new FishingMan(params)
        request.session['user'] = user

        controller.fishingManService.controlPassword(_ as FishingMan) >> { FishingMan u -> u }

        when: "user is connecting"
        controller.connexion()

        then: "user define as session"
        request.session['user'] != null

        and: "user is redirected to home"
        response.redirectedUrl == '/login/index'
    }

    void "test disconnection of the user"() {
        when: "user is already disconnected"
        controller.deconnexion()

        then: "user is redirected to connexion menu"
        response.redirectedUrl == '/login/index'
    }


}
