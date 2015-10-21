package fishandfriends

import grails.test.spock.IntegrationSpec

/**
 * Test for ScoreService.
 *
 * It could be a unit test, BUT grails projections count() doesn't work !
 */
class ScoreServiceSpec extends IntegrationSpec {

    def scoreService = new ScoreService()
    FishingMan user

    void setup(){
        // See bootstrap !
        user = new FishingMan(firstname: "Jean-Michel", email: "jseknm@yahoo.fr", tmpPassword: "password", lastname: "Dupont", gender: "H")
        user.save(flush: true)
    }

    void cleanup(){
        user.delete(flush: true)
    }

    void "test score for a fishingMan"() {
        when: "je récupère les scores de peche"
        FishingManScoreObject result = scoreService.computeScoresForFishingMan(FishingMan.findById(2))

        then:"on a bien les bons scores"
        result.nbCatch == 2
        result.averageWeight == 427.5f
        result.averageSize == 128.1f
    }

    void "test score for a new fishingMan"() {
        when: "je récupère les scores de peche"
        FishingManScoreObject result = scoreService.computeScoresForFishingMan(user)

        then:"on a bien les bons scores"
        result.nbCatch == 0
        result.averageWeight == 0
        result.averageSize == 0
    }

    void "test score for a fishingArea"() {
        when:"je recup les scores d'une fishingArea"
        FishingAreaScoreObject result = scoreService.computeScoresForFishingArea(FishingArea.findById(1))

        then:"on a bien les bons scores"
        result.nbCatch == 1
        result.nbFishingMan == 2
        result.note == 3.5f
    }
}
