package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Catch)
class CatchSpec extends Specification {

    Catch fishCatch
    @Shared FishingArea fishingArea
    @Shared FishingMan fishingMan
    @Shared Fish Fish
    def setup() {
        fishCatch = new Catch()
        fishingMan =
                new FishingMan(
                    firstname: 'azerty',
                    lastname: 'azertry',
                    email: 'azert@azert.fr',
                    hashedPassword: 'qksjnfl',
                    gender: 'H')
        fishingArea =
                new FishingArea(
                        name: 'azetr',
                        location: 'loc')
        fish =
                new Fish(
                        name: 'carpe',
                        weightAverage: 12,
                        sizeAverage: 60
                )
    }

    def cleanup() {
    }

    void "test a valid catch"() {
        given:"a catch"
        fishCatch.date = catchDate
        fishCatch.weight = weightCatch
        fishCatch.size = sizeCatch
        fishCatch.fishingArea = fishingArea
        fishCatch.fishingMan = fishingMan
        fishCatch.fish = fish

        when:"validating the fishCatch"
        def isValid = fishCatch.validate()

        then: "the fishCatch is valid"
        isValid == true

        where:
        catchDate               | weightCatch   | sizeCatch
        new Date(2012, 2, 12)   | 12.2          | 66.1
        new Date(2015, 3, 2)    | 9             | 22



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
        catchDate               | weightCatch   | sizeCatch
        null                    | 12.2          | 66
        new Date(4051,3,12)     | 12.2          | 66
        new Date(2015, 3, 2)    | -2.0          | 22
        new Date(2015, 3, 2)    | 12.2          | -1
        new Date(2015, 3, 2)    | 0.0           | 22
        new Date(2015, 3, 2)    | 12.2          | 0.0
        new Date(2012, 2, 2)    | null          | 66
        new Date(2012, 2, 2)    | 12.2          | null
        new Date(2015, 3, 2)    | 12.2          | 0.0
        new Date(2015, 3, 2)    | 12.2          | 0.0
        new Date(2015, 3, 2)    | 12.2          | 0.0

    }
}
