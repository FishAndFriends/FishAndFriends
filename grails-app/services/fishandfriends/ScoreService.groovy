package fishandfriends

import grails.transaction.Transactional

/**
 * Service to compute Score for a Fishing Man or a Fishing Area.
 */
@Transactional
class ScoreService {

    /**
     * Method used to retrieve all scores for a fishingMan
     * @param f : fishman
     * @return all scores for a fishMan
     */
    public FishingManScoreObject computeScoresForFishingMan(FishingMan f) {
        FishingManScoreObject scoreObject = new FishingManScoreObject()

        def result = Catch.createCriteria().get {

            fishingMan {
                idEq(f.id)
            }

            projections {
                count 'id'
                avg 'size'
                avg 'weight'
            }
        }

        // Set object to return
        if (result[0]) {
            scoreObject.nbCatch = (int) result[0]
        } else {
            scoreObject.nbCatch = 0
        }
        if (result[1]) {
            scoreObject.averageSize = (float) result[1]
        } else {
            scoreObject.averageSize = 0
        }
        if (result[2]) {
            scoreObject.averageWeight = (float) result[2]
        } else {
            scoreObject.averageWeight = 0
        }

        return scoreObject
    }

    /**
     * Method used to retrieve all scores for a fishingArea
     * @param f : fishingArea
     * @return all scores for a fishingArea
     */
    public FishingAreaScoreObject computeScoresForFishingArea(FishingArea f) {
        FishingAreaScoreObject fishingAreaScoreObject = new FishingAreaScoreObject()

        // nbCatch
        def res1 = Catch.createCriteria().get {
            fishingArea {
                idEq(f.id)
            }

            projections {
                count 'id'
            }

            cache true
        }

        // nbFishingMan
        def res2 = FishingArea.createCriteria().get {
            idEq(f.id)

            projections {
                fishingMen {
                    count 'id'
                }
            }

            cache true
        }

        def res3 = Note.createCriteria().get {
            fishingArea {
                idEq(f.id)
            }

            projections {
                avg 'value'
            }

            cache true
        }

        // Set object to return
        if (res1) {
            fishingAreaScoreObject.nbCatch = res1
        } else {
            fishingAreaScoreObject.nbCatch = 0
        }
        if (res2) {
            fishingAreaScoreObject.nbFishingMan = res2
        } else {
            fishingAreaScoreObject.nbFishingMan = 0
        }
        if (res3) {
            fishingAreaScoreObject.note = res3
        } else {
            fishingAreaScoreObject.note = -1
        }

        return fishingAreaScoreObject
    }
}
