package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class SearchFacadeServiceSpec extends IntegrationSpec {

    def searchFacadeService = new SearchFacadeService()

    def setup() {
        // See Bootstrap
    }

    def cleanup() {
    }

    void "test search fishingArea only one"() {
        when: "search fishingArea"
        def result = searchFacadeService.search(0, "fishingArea", "Toulouse")

        then:
        result != null
        result.hasMore == false
        result.result.size() == 1
    }

    void "test search fishingArea multiple"() {
        when: "search fishingArea"
        def result = searchFacadeService.search(0, "fishingArea", "Tou")

        then:
        result != null
        result.hasMore == false
        result.result.size() == 2
    }

    // TODO : other searchEngines !
}
