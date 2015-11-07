package fishandfriends

import fishandfriends.page.LoginPage
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec

/**
 * Created by Alexandre on 07/11/2015.
 */
class CommentFunctionalSpec extends GebSpec {

    def cleanup() {
        CachingDriverFactory.clearCache()
    }

    def "test comment on fishingArea"() {
        when: "Login and search and comment"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()
        sleep(2000)
        $("#query").value('Toulouse')
        $('#searchbtn').click()
        sleep(2000)
        $("[data-id]").click()
        sleep(2000)
        $("#sub0 [name='text']")[0].value('Ceci est un commentaire posté par un test fonctionnel automatique')
        $("#sub0 [type='submit']").click()
        sleep(2000)

        then: "We are redirect to the same page, and the comment is displayed"
        $('body').text().contains('Ceci est un commentaire posté par un test fonctionnel automatique')
    }

    def "test comment on catch"() {
        when: "Login and search and comment"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()
        sleep(2000)
        $("#query").value('Toulouse')
        $('#searchbtn').click()
        sleep(2000)
        $("[data-id]").click()
        sleep(2000)
        def text = Integer.parseInt($('[data-ident]')[0].text().split(" ")[0])
        $('[data-ident]')[0].click()
        sleep(5000)
        $("#sub1 [name='text']")[0].value('Ceci est un commentaire posté par un test fonctionnel automatique')
        $("#sub1 [type='submit']").click()
        sleep(2000)

        then: "We are redirect to the same page and the number of comment is increased for the first catch"
        $("[data-ident]")[0].text().split(" ")[0] == (text + 1) + ""
    }
}
