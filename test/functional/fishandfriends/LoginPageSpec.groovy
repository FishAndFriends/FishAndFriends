package fishandfriends

import fishandfriends.page.LoginPage
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec
import org.openqa.selenium.By

/**
 * Created by Alexandre on 05/11/2015.
 */
class LoginPageSpec extends GebSpec {
    def setup() {

    }

    def cleanup() {
        CachingDriverFactory.clearCache()
    }

    def "creation of a user"() {
        when: "Fill the form"
        to LoginPage
        $("[name='signupFirstname']").value('USER_NAME')
        $("[name='signupLastname']").value('USER_LAST_NAME')
        $("[name='signupMail']").value('jTESTm@yahoo.fr')
        $("[name='signupPwd']").value('PASSWORD')
        $("#male").click()
        $("#subsignin").click()

        then: "we are redirected to the newsfeed page and we are logged"
        $('body').text().contains('Fil d\'actualités')
        $('body').text().contains('USER_NAME')
        $('body').text().contains('Déconnexion')

        when: "disconnect"
        $("#signout").click()
        sleep(1000)
        $("#logoutbtn").click()

        then: "we are at the login / signin form"
        // Check presence of signin submit button
        //js. ==> Used to inject JS to determine the presence of an element
        js.($("#signout")[0] != null)
    }

    def "test login"() {
        when: "Fill the form"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()

        then: "we are redirected to the newsfeed page and we are logged"
        $('body').text().contains('Fil d\'actualités')
        $('body').text().contains('Jean-Michel Dupont')
        $('body').text().contains('Déconnexion')
    }
}
