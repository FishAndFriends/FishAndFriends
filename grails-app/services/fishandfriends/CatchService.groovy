package fishandfriends

import grails.transaction.Transactional

@Transactional
class CatchService {

    def getCatchesByFishingMan(FishingMan afishingMan) {
        def criteria = Catch.createCriteria()

        def result = criteria.list() {
            fishingMan {
                idEq(afishingMan.id)

            }
        }
    }

    def getCatchesByFishingArea(FishingArea afishingArea) {
        def criteria = Catch.createCriteria()

        def result = criteria.list() {
            fishingArea {
                idEq(afishingArea.id)
            }
        }
    }


    /**
     * Insert or update the Catch define by <i>aCatch</i>.
     * @param aCatch Catch to save into database.
     * @return Catch stored.
     */
    Catch insertOrUpdateCatch(Catch aCatch) {
        aCatch.save(flush: true)
    }
}
