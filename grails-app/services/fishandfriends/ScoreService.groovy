package fishandfriends

import grails.transaction.Transactional

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
        if (result) {
            scoreObject.nbCatch = result[0]
            scoreObject.averageSize = result[1]
            scoreObject.averageWeight = result[2]
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
            projections {
                count 'fishingMen'
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
        }
        if (res2) {
            fishingAreaScoreObject.nbFishingMan = res2
        }
        if (res3) {
            fishingAreaScoreObject.note = res3
        }

        return fishingAreaScoreObject
    }
}
