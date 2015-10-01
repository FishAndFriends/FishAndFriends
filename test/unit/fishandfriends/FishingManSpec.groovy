package fishandfriends

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(FishingMan)
class FishingManSpec extends Specification {

    FishingMan fishingMan
    def setup() {

        fishingMan = new FishingMan()
    }

    def cleanup() {
    }

    @Unroll
    def "test a valid fishingman"() {
        given:"a fishingman"
        fishingMan.firstname = aFirstname
        fishingMan.email = anEmail
        fishingMan.password = aPassword
        fishingMan.lastname = aLastname
        fishingMan.gender = aGender


        when:"validating the fishingman"
        def isValid = fishingMan.validate()

        then:"the fishingman is valid"
        isValid == true

        where:
        aFirstname    | anEmail              |  aPassword    |  aLastname |  aGender
        "Jean-Michel" | "jm@yahoo.fr"        |  "mypassword" |  "Dupont"  |   "H"
        "Jaqueline"   | "jaqueline@yahoo.fr" |  "azerty"     |  "Martin"  |   "F"
        "Pierre"      | "pierre@yahoo.fr"    |  "azerty1234" |  "Durant"  |   "H"
    }

    @Unroll
    def "test an invalid fishingman"() {
        given:"a fishingman"
        fishingMan.firstname = aFirstname
        fishingMan.email = anEmail
        fishingMan.password = aPassword
        fishingMan.lastname = aLastname
        fishingMan.gender = aGender


        when:"validating the fishingman"
        def isValid = fishingMan.validate()

        then:"the fishingman is not valid"
        isValid == false

        where:
        aFirstname    | anEmail              |  aPassword    |  aLastname |  aGender
        null          | "jm@yahoo.fr"        |  "mypassword" |  "Dupont"  |   "H"
        "Jaqueline"   | "jaqueline.fr"       |  "azerty"     |  "Dupont"  |   "F"
        "Michelline"  | "jaqueline@yahoo.fr" |  "a"          |  "Dupont"  |   "F"
        "Jaqueline"   | "jaqueline@yahoo.fr" |  "azerty"     |  null      |   "K"
        ""            | "jaqueline@yahoo.fr" |  "azerty"     |  "Dupont"  |   "F"
        "Michel"      | "michel@laposte.net" |   null        | "LaForet"  |   "H"
        "Jean"        | null                 |  "papaaaa"    | "Pierre"   |   "F"
        "pierro"      | "dada@ada.com"       |  "hhhhhhh"    | ""         |  "H"
        "Maurice"     | ""                   |  "aoaoaoaoa"  | "Alama"    |  "F"
        "Bobby"       | "supbby@gmail.com"   |  ""           | "lafleche" |  "H"
      }
}
