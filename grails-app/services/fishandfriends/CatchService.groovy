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


}
