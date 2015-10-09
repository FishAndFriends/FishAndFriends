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

    void "test search error"(){
        when: "search with an unknown type"
        boolean isError = false
        try{
            def result = searchFacadeService.search(0,"Roll your head on your keyboard !", "It's fun, you know !")
            isError = false
        }catch(RuntimeException e){
            isError = true
        }

        then:"Error is thrown"
        isError == true
    }

    void "test search fishingMan"() {
        when: "search fishingMan"
        def result = searchFacadeService.search(0,"fishingMan","Jean")
        System.out.println FishingMan.count()

        then:"Result with 1 fishingMan"
        result != null
        result.hasMore == false
        result.result.size() == 1
        result.result[0] instanceof FishingMan
    }

    void "test search fish"() {
        when: "search fishingMan"
        def result = searchFacadeService.search(0,"fish","Nemo")

        then:"Result with 1 fish"
        result != null
        result.hasMore == false
        result.result.size() == 1
        result.result[0] instanceof Fish
    }
}
