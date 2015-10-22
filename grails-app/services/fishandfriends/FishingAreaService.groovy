package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingAreaService implements ISearchService{

    def search(int max, int offset, String query) {
        def criteria = FishingArea.createCriteria()

        List<FishingArea> result = criteria.list(max: max, offset: offset) {
            or {
                ilike 'name', "%${query}%"
                ilike 'location',"%${query}%"
            }
        }
        result
    }


    def getFishingAreaByFishingMan(FishingMan afishingMan) {
        def criteria = FishingArea.createCriteria()

        def result = criteria.list() {
            fishingMen {
                idEq(afishingMan.id)
            }
        }
        result
    }


}
