package fishandfriends

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FishingManService)
@Mock([FishingMan])
class FishingManServiceSpec extends Specification {

    FishingMan fishingMan, fishingMan1, fishingMan2, fishingMan3, fishingMan4, fishingMan5

    def setup() {
        fishingMan = new FishingMan(firstname: 'azerty',
                lastname: 'azertry',
                email: 'azert@azert.fr',
                tmpPassword: 'CaCestUnBonMdp',
                gender: 'H')

        fishingMan1 = new FishingMan(firstname: 'Chris',
                lastname: 'Paul',
                email: 'chris.paul@gmail.com',
                tmpPassword: 'password1',
                gender: 'H')

        fishingMan2 = new FishingMan(firstname: 'Chris',
                lastname: 'Griffin',
                email: 'chris.griffin@yahoo.fr',
                tmpPassword: 'password2',
                gender: 'H')

        fishingMan3 = new FishingMan(firstname: 'Lebron',
                lastname: 'Jame',
                email: 'lebron.james@hotmail.fr',
                tmpPassword: 'password3',
                gender: 'H')

        fishingMan4 = new FishingMan(firstname: 'Kevin',
                lastname: 'Durant',
                email: 'kevin.durant@gmail.com',
                tmpPassword: 'password4',
                gender: 'H')

        fishingMan5 = new FishingMan(firstname: 'Jane',
                lastname: 'Smith',
                email: 'jane.smith@laposte.fr',
                tmpPassword: 'password5',
                gender: 'F')

        service.insertOrUpdateFishingMan(fishingMan1)
        service.insertOrUpdateFishingMan(fishingMan2)
        service.insertOrUpdateFishingMan(fishingMan3)
        service.insertOrUpdateFishingMan(fishingMan4)
        service.insertOrUpdateFishingMan(fishingMan5)
    }

    def cleanup() {
        fishingMan.delete(flush: true)
        fishingMan1.delete(flush: true)
        fishingMan2.delete(flush: true)
        fishingMan3.delete(flush: true)
        fishingMan4.delete(flush: true)
        fishingMan5.delete(flush: true)
    }


    void "test insert or update of a fishingMan"() {
        given: "a FishingMan"
        when: "we try to retreive the fishingMan from the database "
        FishingMan resFishingMan = service.insertOrUpdateFishingMan(fishingMan)

        then: "the fishingMan is the same as the initial fishingMan"
        resFishingMan == fishingMan

        and: "the result has no errors"
        !resFishingMan.hasErrors()
        and: "the result has an id"
        resFishingMan.id
        and: "the fishingMan is present in the database"
        FishingMan.findById(resFishingMan.id) != null
    }


    void "test a valid search multiple fishingman"() {
        when: "searching for fishingmen whith firstname"
        def result = service.search(5, 0, "Chris")

        then: "the list of all fishing man with this name is displayed"
        result.size() == 2
        result.contains(fishingMan1)
        result.contains(fishingMan2)
    }

    void "test a valid search of a fishingman"() {
        when: "searching for a fishingman with lastname "
        def result = service.search(5, 0, "Smith")

        then: "the list of all fishingman with this lastname"
        result.size() == 1
        result.contains(fishingMan5)
        !result.contains(fishingMan3)
    }

    void "test an invalid search of fishingman"() {
        when: "searching for a fishingman that does not exist"
        def result = service.search(5, 0, "fjebefjhsf")

        then: "the list is empty"
        result.size() == 0
        !result.contains(fishingMan1)
        !result.contains(fishingMan2)
    }

    void "test to log with a correct password"() {
        when:"a fishingman want to log in with the right password"
        def result = service.controlPassword(fishingMan1, "password1")

        then: "the password is considered correct"
        result == true
    }

    void "test to log with a wrong password"() {
        when:"a fishingman want to log in with a wrong password"
        def result = service.controlPassword(fishingMan1, "lolilol")

        then: "the password is considered incorrect"
        result == false
    }


    void "test to log with an non-existant fishingman"() {
        when:"a user try to log in with a wrong mail"
        def fishingManTmp = service.findByEmail("faux@mail.com")
        def result = service.controlPassword(fishingManTmp, "lolilol")

        then: "the user is not logged in"
        result == false
    }

}
