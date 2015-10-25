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

    def isSuscriberToArea(FishingArea fishingAreaInstance, FishingMan fishingMan) {
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
        Boolean result;
        if (isIn > 0) {
            //Le fishing Man se désabonne
            result = true
        } else {
           result = false
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
