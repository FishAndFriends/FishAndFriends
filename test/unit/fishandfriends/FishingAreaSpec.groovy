package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(FishingArea)
class FishingAreaSpec extends Specification {

    FishingArea fishingArea
    def setup() {
        fishingArea = new FishingArea()
    }

    def cleanup() {
    }

    @Unroll
    def "test a valid fishing area"() {
        given:"a fishing area"

        fishingArea.name = aName
        fishingArea.location = aLocation
        fishingArea.description = aDescription

        when:"validating the fishing area"
        def isValid = fishingArea.validate()

        then:"the fishing area is valid"
        isValid == true

        where:

        aName         |  aLocation  | aDescription
        "La garonne"  | "Toulouse"  | "Ce lieu est superbe"
        "La garonne"  | "Toulouse"  | "ce super lieu"

    }

    @Unroll
    def "test a invalid fishing area"() {
        given:"a fishing area"

        fishingArea.name = aName
        fishingArea.location = aLocation
        fishingArea.description = aDescription

        when:"validating the fishing area"
        def isValid = fishingArea.validate()

        then:"the fishing area is not valid"
        isValid == false

        where:
        aName         |  aLocation | aDescription
        "La garonne"  | null       | "Lekekekke"
        "La garonne"  | ""         | "Beau panorama"
        ""            | "Toulouse" | "Belle vue"
        null          | "Toulouse" | "Soleil levant"
        "La garonne"  | "Paris "   | null
        "La garonne"  | "Lyon"     | ""

    }


}
