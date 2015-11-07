package fishandfriends

import grails.transaction.Transactional

/**
 * Service to store Fishing Area in database or subscribe and unsubscribe a
 * Fishing Man to a Fishing Area.
 */
@Transactional
class FishingAreaDAOService {

    /**
     * Store in database and return the Fishing area <i>fishingArea</i>.
     *
     * @param fishingArea Fishing Area to store in database.
     * @return Fishing Area stored.
     */
    def saveFishingArea(FishingArea fishingArea) {
        fishingArea.save(flush: true, failOnError: true)
    }

    /**
     * Subscribe or unsubscribe a Fishing Man <i>fishingMan</i> to the Fishing
     * Area <i>fishingAreaInstance</i>.
     *
     * @param fishingAreaInstance Fishing Area to subscribe/unsubscribe.
     * @param fishingMan FishingMan who subscribes/unsubscribes to Fishing Area.
     * @return Fishing Area to subscribe/unsubscribe.
     */
    def suscribeUnsuscribeToArea(FishingArea fishingAreaInstance,
                                 FishingMan fishingMan) {
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
