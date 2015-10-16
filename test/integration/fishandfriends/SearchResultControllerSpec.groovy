package fishandfriends

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class SearchResultControllerSpec extends Specification {

    def searchResultController = new SearchResultController()

//    def setup() {
//        //See bootstrap
//    }

    void "test index"() {
        given:
        searchResultController.params.want = "fishingArea"
        searchResultController.params.query = "Toulouse"

        when:
        searchResultController.index()

        then:
        searchResultController.modelAndView.model.page == 0
        searchResultController.modelAndView.model.query == "Toulouse"
        searchResultController.modelAndView.model.want == "fishingArea"
        searchResultController.modelAndView.model.hasMore == false
        searchResultController.modelAndView.model.result != null
    }

    void "test next"() {
        given:
        searchResultController.params.page = "0"
        searchResultController.params.want = "fishingArea"
        searchResultController.params.query = "Toulouse"

        when:
        searchResultController.nextPage()

        then:
        searchResultController.modelAndView.model.page == 1
        searchResultController.modelAndView.model.query == "Toulouse"
        searchResultController.modelAndView.model.want == "fishingArea"
        searchResultController.modelAndView.model.hasMore == false
        searchResultController.modelAndView.model.result != null
    }

    void "test prev"() {
        given:
        searchResultController.params.page = "1"
        searchResultController.params.want = "fishingArea"
        searchResultController.params.query = "Toulouse"

        when:
        searchResultController.prevPage()

        then:
        searchResultController.modelAndView.model.page == 0
        searchResultController.modelAndView.model.query == "Toulouse"
        searchResultController.modelAndView.model.want == "fishingArea"
        searchResultController.modelAndView.model.hasMore == false
        searchResultController.modelAndView.model.result != null
    }

    void "test bad search type"(){
        given:
        searchResultController.params.page = "1"
        searchResultController.params.want = "Macho macho man !"
        searchResultController.params.query = "I gotta be a macho man !"

        when:
        searchResultController.index()

        then:
        searchResultController.modelAndView.model.error == true
    }
}
