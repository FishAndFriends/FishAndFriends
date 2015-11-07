package fishandfriends

import fishandfriends.page.LoginPage
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec

/**
 * Created by Alexandre on 06/11/2015.
 */
class SearchSpec extends GebSpec {

    def cleanup() {
        CachingDriverFactory.clearCache()
    }

    def "test search fishingArea"() {
        when: "Login and search"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()
        sleep(1000)
        $("#query").value('Toulouse')
        $('#searchbtn').click()

        then: "We are redirect to a page which contains the results with good links"
        $('body').text().contains('Toulouse')
        $("[data-id]")[0].click()
        // Check that we are on the Area 1
        js.($("[action='/fishingArea/suscribeUnsuscribeToArea/']") != null)
    }

    def "test search fish"() {
        when: "Login and search"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()
        sleep(1000)
        $('#want').value('fish')
        $("#query").value('Nemo')
        $('#searchbtn').click()

        then: "We are redirect to a page which contains the results with good links"
        $('body').text().contains('Nemo')
        $('body').text().contains('Poids: 2.0 kg, Taille: 5.0 cm')
    }

    def "test search fishingMan"() {
        when: "Login and search"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()
        sleep(1000)
        $('#want').value('fishingMan')
        $("#query").value('jean')
        $('#searchbtn').click()

        then: "We are redirect to a page which contains the results with good links"
        $('body').text().contains('Jean-Michel Dupont')
        $("[data-id]")[0].click()
        $('body').text().contains('Modifier')
    }
}
