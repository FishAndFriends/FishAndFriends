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
        fishingMan.hashedPassword = aPassword
        fishingMan.saltedPassword = aPassword
        fishingMan.lastname = aLastname
        fishingMan.gender = aGender


        when:"validating the fishingman"
        def isValid = fishingMan.validate()

        then:"the fishingman is valid"
        isValid == true

        where:
        aFirstname    | anEmail              |  aPassword    |  aLastname |  aGender
        "Jean-Michel" | "jm@yahoo.fr"        |  "test"       |  "Dupont"  |   "H"
        "Jaqueline"   | "jaqueline@yahoo.fr" |  "test"       |  "Martin"  |   "F"
        "Pierre"      | "pierre@yahoo.fr"    |  "test"       |  "Durant"  |   "H"
    }

    @Unroll
    def "test an invalid fishingman"() {
        given:"a fishingman"
        fishingMan.firstname = aFirstname
        fishingMan.email = anEmail
        fishingMan.hashedPassword = aPassword
        fishingMan.saltedPassword = aPassword
        fishingMan.lastname = aLastname
        fishingMan.gender = aGender


        when:"validating the fishingman"
        def isValid = fishingMan.validate()

        then:"the fishingman is not valid"
        isValid == false

        where:
        aFirstname    | anEmail              |  aPassword    |  aLastname |  aGender
        null          | "jm@yahoo.fr"        |  "test"       |  "Dupont"  |   "H"
        "Jaqueline"   | "jaqueline.fr"       |  "test"       |  "Dupont"  |   "F"
        "Jaqueline"   | "jaqueline@yahoo.fr" |  "test"       |  null      |   "K"
        ""            | "jaqueline@yahoo.fr" |  "test"       |  "Dupont"  |   "F"
        "Jean"        | null                 |  "test"       | "Pierre"   |   "F"
        "pierro"      | "dada@ada.com"       |  "test"       | ""         |  "H"
        "Maurice"     | ""                   |  "test"       | "Alama"    |  "F"
      }
}
