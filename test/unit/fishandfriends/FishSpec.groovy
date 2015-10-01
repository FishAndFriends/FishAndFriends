package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Fish)
class FishSpec extends Specification {

    Fish fish
    def setup() {
        fish = new Fish()
    }

    def cleanup() {
    }

    @Unroll
    def "test a valid fish"() {
        given:"a fish"
        fish.name = aName
        fish.weightAverage = aWeight
        fish.sizeAverage = aSize


        when:"validating the fish"
        def isValid = note.validate()

        then:"the fish is valid"
        isValid == true

        where:
        aName   | aWeight | aSize
        "Carpe" | 10      | 1
        "Diem"  | 1       | 50.3
    }

    @Unroll
    def "test an invalid fish"() {
        given:"a fish"
        fish.name = aName
        fish.weightAverage = aWeight
        fish.sizeAverage = aSize


        when:"validating the fish"
        def isValid = note.validate()

        then:"the fish is not valid"
        isValid == false

        where:
        aName   | aWeight | aSize
        ""      | 10      | 1
        null    | 1       | 50.3
        "Carpe" | 10      | -1
        "Diem"  | -1      | 50.3
    }
}
