package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */

class FishingManServiceSpec extends IntegrationSpec {

    def fishingManService = new FishingManService()
    FishingMan fishingMan

    def setup() {
        fishingMan = new FishingMan(
                firstname: 'azerty',
                lastname: 'azertry',
                email: 'azert@azert.fr',
                hashedPassword: 'qksjnfl',
                gender: 'H')
    }

    def cleanup() {
    }

    void "test insert or update of a fishingMan"() {
        given:"a FishingMan"
        when:"we try to retreive the fishingMan from the database "
        FishingMan resFishingMan = fishingManService.insertOrUpdateFishingMan(fishingMan)

        then:"the fishingMan is the same as the initial fishingMan"
        resFishingMan == fishingMan

        and:"the result has no errors"
        !resFishingMan.hasErrors()
        and:"the result has an id"
        resFishingMan.id
        and:"the fishingMan is present in the database"
        FishingMan.findById(resFishingMan.id) != null
    }
}
