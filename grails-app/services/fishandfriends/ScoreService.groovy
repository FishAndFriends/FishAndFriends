package fishandfriends

import grails.transaction.Transactional

@Transactional
class ScoreService {

    /**
     * Method used to retrieve all scores for a fishingMan
     * @param f : fishman
     * @return all scores
     */
    public ScoreObject computeScores(FishingMan f){
        ScoreObject scoreObject = new ScoreObject()

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
        if(result?.size()>0) {
            scoreObject.nbCatch = result[0][0]
            scoreObject.averageSize = result[0][1]
            scoreObject.averageWeight = result[0][2]
        }

        return scoreObject
    }
}
