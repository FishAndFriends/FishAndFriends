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
}
