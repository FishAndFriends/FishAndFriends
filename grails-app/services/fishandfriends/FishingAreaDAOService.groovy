package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingAreaDAOService {

    def saveFishingArea(FishingArea fishingArea) {

        fishingArea.save(flush: true, failOnError: true)
    }

    def suscribeUnsuscribeToArea(FishingArea fishingAreaInstance, FishingMan fishingMan) {
        fishingMan = FishingMan.findById(fishingMan.id)
        //Test already suscriber
        def isIn = FishingArea.createCriteria().get {
            idEq(fishingAreaInstance.id)

            fishingMen {
                idEq(fishingMan.id)

                projections {
                    count 'id'
                }
            }

        }

        if (isIn > 0) {
            //Le fishing Man se désabonne
            fishingAreaInstance.removeFromFishingMen(fishingMan)
            fishingAreaInstance.save(flush: true)
        } else {
            //Le fishing Man s'abonne
            fishingAreaInstance.addToFishingMen(fishingMan)
            fishingAreaInstance.save(flush: true)
        }
    }
}
