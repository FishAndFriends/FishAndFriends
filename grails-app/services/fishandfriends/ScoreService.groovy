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

        def result = Catch.createCriteria().list {

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
        if (result?.size() > 0) {
            scoreObject.nbCatch = result[0][0]
            scoreObject.averageSize = result[0][1]
            scoreObject.averageWeight = result[0][2]
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
        def res1 = Catch.createCriteria().list {

            fishingArea {
                idEq(f.id)
            }

            projections {
                count 'id'
            }
        }

        // nbFishingMan
        def res2 = FishingArea.createCriteria().list {
            projections {
                count 'fishingMen'
            }
        }

        // Set object to return
        if (res1?.size() > 0) {
            fishingAreaScoreObject.nbCatch = res1[0]
        }
        if (res2?.size() > 0) {
            fishingAreaScoreObject.nbFishingMan = res2[0]
        }

        return fishingAreaScoreObject
    }
}
