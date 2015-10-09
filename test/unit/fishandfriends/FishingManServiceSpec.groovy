package fishandfriends

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.spock.IntegrationSpec
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FishingManService)
@Mock([FishingMan])
class FishingManServiceSpec extends Specification {

    FishingMan fishingMan,fishingMan1,fishingMan2,fishingMan3,fishingMan4,fishingMan5

    def setup() {
        fishingMan = new FishingMan(firstname: 'azerty',
                lastname: 'azertry',
                email: 'azert@azert.fr',
                hashedPassword: 'qksjnfl',
                saltedPassword: 'ezkenfjkez',
                gender: 'H').save(flush: true)

        fishingMan1 = new FishingMan(firstname: 'Chris',
                lastname: 'Paul',
                email: 'chris.paul@gmail.com',
                hashedPassword: 'password1',
                saltedPassword: 'njzknskejhdd',
                gender: 'H').save(flush: true)

        fishingMan2 = new FishingMan(firstname: 'Chris',
                lastname: 'Griffin',
                email: 'chris.griffin@yahoo.fr',
                hashedPassword: 'password2',
                saltedPassword: 'zizfufbenrfnc',
                gender: 'H').save(flush: true)

        fishingMan3 = new FishingMan(firstname: 'Lebron',
                lastname: 'Jame',
                email: 'lebron.james@hotmail.fr',
                hashedPassword: 'password3',
                saltedPassword: 'lzkenlfknrlkn',
                gender: 'H').save(flush: true)

        fishingMan4 = new FishingMan(firstname: 'Kevin',
                lastname: 'Durant',
                email: 'kevin.durant@gmail.com',
                hashedPassword: 'password4',
                saltedPassword: 'zlefkelnkfr',
                gender: 'H').save(flush: true)

        fishingMan5 = new FishingMan(firstname: 'Jane',
                lastname: 'Smith',
                email: 'jane.smith@laposte.fr',
                hashedPassword: 'password5',
                saltedPassword: 'efnfklend',
                gender: 'F').save(flush: true)
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
        given:"a FishingMan"
        when:"we try to retreive the fishingMan from the database "
        FishingMan resFishingMan = service.insertOrUpdateFishingMan(fishingMan)

        then:"the fishingMan is the same as the initial fishingMan"
        resFishingMan == fishingMan

        and:"the result has no errors"
        !resFishingMan.hasErrors()
        and:"the result has an id"
        resFishingMan.id
        and:"the fishingMan is present in the database"
        FishingMan.findById(resFishingMan.id) != null
    }


    void "test a valid search multiple fishingman"() {
        when:"searching for fishingmen whith firstname"
        def result = service.search(5,0,"Chris")

        then:"the list of all fishing man with this name is displayed"
        result.size() == 2
        result.contains(fishingMan1)
        result.contains(fishingMan2)
    }

    void "test a valid search of a fishingman"() {
        when:"searching for a fishingman with lastname "
        def result = service.search(5,0,"Smith")

        then:"the list of all fishingman with this lastname"
        result.size() == 1
        result.contains(fishingMan5)
        !result.contains(fishingMan3)
    }

    void "test an invalid search of fishingman"() {
        when:"searching for a fishingman that does not exist"
        def result = service.search(5,0,"fjebefjhsf")

        then:"the list is empty"
        result.size() == 0
        !result.contains(fishingMan1)
        !result.contains(fishingMan2)
    }





}
