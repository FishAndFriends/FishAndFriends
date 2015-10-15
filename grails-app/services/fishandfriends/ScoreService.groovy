package fishandfriends

import grails.transaction.Transactional

@Transactional
class ScoreService {

    public ScoreObject computeScores(FishingMan f){
        ScoreObject scoreObject = new ScoreObject()

        def result = Catch.createCriteria().list {

            fishingMan {
                idEq(f.id)
            }

            projections {
                groupProperty "fishingMan.id"
                count 'id'
                avg 'size'
                avg 'weight'
            }
        }

        if(result?.size()>0) {
            scoreObject.nbCatch = result[0][0]
            scoreObject.averageSize = result[0][1]
            scoreObject.averageWeight = result[0][2]
        }

        return scoreObject
    }
}
