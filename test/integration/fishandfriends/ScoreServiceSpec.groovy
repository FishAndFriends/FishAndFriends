package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * Test for ScoreService.
 *
 * It could be a unit test, BUT grails projections count() doesn't work !
 */
class ScoreServiceSpec extends IntegrationSpec {

    def scoreService = new ScoreService()

    void "test score"() {
        when: "je récupère les scores de peche"
        ScoreObject result = scoreService.computeScores(FishingMan.findById(2))

        then:"on a bien les bons scores"
        result.nbCatch == 2
        result.averageWeight == 427.5f
        result.averageSize == 128.1f
    }
}
