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
        fishingMan.lastname = aLastname
        fishingMan.gender = aGender
        fishingMan.tmpPassword = aTmpPassword


        when:"validating the fishingman"
        def isValid = fishingMan.validate()

        then:"the fishingman is valid"
        isValid == true

        where:
        aFirstname    | anEmail              |  aPassword    |  aLastname |  aGender    |   aTmpPassword
        "Jean-Michel" | "jm@yahoo.fr"        |  "test"       |  "Dupont"  |   "H"       |   "jeanmi42"
        "Jaqueline"   | "jaqueline@yahoo.fr" |  "test"       |  "Martin"  |   "F"       |   "jackdaniel7"
        "Pierre"      | "pierre@yahoo.fr"    |  "test"       |  "Durant"  |   "H"       |   "pierro42"
        "Victor"      | "vigor@yahoo.fr"     |  "test"       |  "Dupont"  |   "H"       |   null
    }

    @Unroll
    def "test an invalid fishingman"() {
        given:"a fishingman"
        fishingMan.firstname = aFirstname
        fishingMan.email = anEmail
        fishingMan.hashedPassword = aPassword
        fishingMan.lastname = aLastname
        fishingMan.gender = aGender
        fishingMan.tmpPassword = aTmpPassword


        when:"validating the fishingman"
        def isValid = fishingMan.validate()

        then:"the fishingman is not valid"
        isValid == false

        where:
        aFirstname    | anEmail              |  aPassword    |  aLastname |  aGender    |   aTmpPassword
        null          | "jm@yahoo.fr"        |  "test"       |  "Dupont"  |   "H"       |   "testouille42"
        "Jaqueline"   | "jaqueline.fr"       |  "test"       |  "Dupont"  |   "F"       |   "testouille42"
        "Jaqueline"   | "jaqueline@yahoo.fr" |  "test"       |  null      |   "K"       |   "testouille42"
        ""            | "jaqueline@yahoo.fr" |  "test"       |  "Dupont"  |   "F"       |   "testouille42"
        "Jean"        | null                 |  "test"       | "Pierre"   |   "F"       |   "testouille42"
        "pierro"      | "dada@ada.com"       |  "test"       | ""         |   "H"       |   "testouille42"
        "Maurice"     | ""                   |  "test"       | "Alama"    |   "F"       |   "testouille42"
        "Jack"        | "jm@yahoo.fr"        |  "test"       | "Daniel"   |   "F"       |   "less8"
      }
}
