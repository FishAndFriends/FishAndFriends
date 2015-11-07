package fishandfriends

import fishandfriends.page.LoginPage
import geb.driver.CachingDriverFactory
import geb.spock.GebSpec

/**
 * Created by Alexandre on 07/11/2015.
 */
class FishingAreaNoteSpec extends GebSpec {
    def cleanup() {
        CachingDriverFactory.clearCache()
    }

    def "test note on fishingArea"() {
        when: "Login and search and note the fishingArea"
        to LoginPage
        $("[name='connectMail']").value('jm@yahoo.fr')
        $("[name='connectPwd']").value('password')
        $("#sublogin").click()
        sleep(2000)
        $("#query").value('')
        $('#searchbtn').click()
        sleep(2000)
        $("[data-id]")[1].click()
        sleep(2000)
        assert $("[name='submit']").value() == "Noter"
        $("[name='submit']").click()
        sleep(2000)

        then: "We are redirect to the same page, and the note button label is changed"
        $("[name='submit']").value() == "Changer"
    }
}
