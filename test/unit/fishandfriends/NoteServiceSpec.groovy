package fishandfriends

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(NoteService)
@Mock([FishingMan, FishingArea, Note])
class NoteServiceSpec extends Specification {

    Note note
    FishingMan fishingMan
    FishingArea fishingArea

    def setup() {
        fishingArea = new FishingArea(location: "Mars", name: "Viens pêcher des aliens", description: "Si tu aimes le vert, c'est pour toi !")
        fishingMan = new FishingMan(firstname: 'azerty', lastname: 'azertry', email: 'azjert@azert.fr', hashedPassword: 'qksjnfl', saltedPassword: 'zekjvbzk', gender: 'H')

        fishingArea.save(flush: true)
        fishingMan.save(flush: true)
    }

    def cleanup() {
        fishingArea.delete(flush: true)
        fishingMan.delete(flush: true)
    }

    void "test insert note"() {
        given:
        def n = Note.count()

        when: "I put a note on a fishing Area"
        def res = service.insertOrUpdate(fishingMan, fishingArea, 5)

        then: "a new instance is stored on the DB"
        Note.count() == n + 1
        res.id == n + 1

        res.delete(flush: true)
    }

    void "test update note"() {
        given:
        note = new Note(fishingMan: fishingMan, fishingArea: fishingArea, value: 5)
        note.save(flush: true)
        def n = Note.count()

        when: "I put a note on a fishing Area"
        def res = service.insertOrUpdate(fishingMan, fishingArea, 1)

        then: "a new instance is stored on the DB"
        res.id == n
        res.value == 1
        Note.count() == n
    }

    void "test retrieve note given by a fishingMan"() {
        when: "Note not given by the fishingMan"
        def val = service.getNoteGivenByAFishingMan(FishingMan.findById(1), FishingArea.findById(1))

        then: "The result is 0"
        val == 0
    }

    void "test retrieve note given by a fishingMan - exist "() {
        given:
        note = new Note(fishingMan: fishingMan, fishingArea: fishingArea, value: 5)
        note.save(flush: true)

        when: "Note not given by the fishingMan"
        def val = service.getNoteGivenByAFishingMan(FishingMan.findById(1), FishingArea.findById(1))

        then: "The result is 5"
        val == 5
    }
}
