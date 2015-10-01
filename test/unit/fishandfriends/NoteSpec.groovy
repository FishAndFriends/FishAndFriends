package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Note)
class NoteSpec extends Specification {

    Note note
    def setup() {
        note = new Note()
    }

    def cleanup() {
    }

    @Unroll
    def "test a valid note"() {
        given:"a note"
        note.fishingMan = aFishingMan
        note.value = aValue
        note.fishingArea = aFishingArea


        when:"validating the note"
        def isValid = note.validate()

        then:"the note is valid"
        isValid == true

        where:
        aFishingMan      | aFishingArea      | aValue
        Mock(FishingMan) | Mock(FishingArea) | 1
        Mock(FishingMan) | Mock(FishingArea) | 5
    }


    @Unroll
    def "test invalid note"() {
        given:"a note"
        note.fishingMan = aFishingMan
        note.value = aValue
        note.fishingArea = aFishingArea


        when:"validating the note"
        def isValid = note.validate()

        then:"the note is valid"
        isValid == false

        where:
        aFishingMan      | aFishingArea      | aValue
        Mock(FishingMan) | Mock(FishingArea) | -1
        Mock(FishingMan) | Mock(FishingArea) | 10
    }
}
