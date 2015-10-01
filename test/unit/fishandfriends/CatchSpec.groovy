package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Catch)
class CatchSpec extends Specification {

    Catch fishCatch

    def setup() {
        fishCatch = new Catch()
    }

    def cleanup() {
    }

    void "test a valid catch"() {
        given:"a catch"
        fishCatch.date = catchDate
        fishCatch.weight = weightCatch
        fishCatch.size = sizeCatch

        when:"validating the fishCatch"
        def isValid = fishCatch.validate()

        then: "the fishCatch is valid"
        isValid == true

        where:
        catchDate               | weightCatch   | sizeCatch | fishingManCatch   | fishingAreaCatch
        new Date(2012, 2, 12)   | 12.2          | 66        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2015, 3, 2)    | 9             | 22        | Mock(FishingMan)  | Mock(FishingArea)


    }

    void "test an invalid catch"() {
        given:"a catch"
        fishCatch.date = catchDate
        fishCatch.weight = weightCatch
        fishCatch.size = sizeCatch

        when:"validating the fishCatch"
        def isValid = fishCatch.validate()

        then: "the fishCatch is not valid"
        isValid == false

        where:
        catchDate               | weightCatch   | sizeCatch | fishingManCatch   | fishingAreaCatch
        null                    | 12.2          | 66        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(4051,3,12)     | 12.2          | 66        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2015, 3, 2)    | -2.0          | 22        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2015, 3, 2)    | 12.2          | -1        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2015, 3, 2)    | 0.0           | 22        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2015, 3, 2)    | 12.2          | 0.0       | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2012, 2, 2)    | null          | 66        | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2012, 2, 2)    | 12.2          | null      | Mock(FishingMan)  | Mock(FishingArea)
        new Date(2015, 3, 2)    | 12.2          | 0.0       | null              | Mock(FishingArea)
        new Date(2015, 3, 2)    | 12.2          | 0.0       | Mock(FishingMan)  | null
        new Date(2015, 3, 2)    | 12.2          | 0.0       | null              | null

    }
}
