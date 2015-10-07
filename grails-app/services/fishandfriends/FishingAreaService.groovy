package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingAreaService {



    def serviceMethod() {

    }



    List<FishingArea> getFishingAreaByNameOrLocation(String value) {

        def criteria = FishingArea.createCriteria()

        List<FishingArea> result = criteria.list {
            or {
                ilike 'name', "%${value}%"
                ilike 'location',"%${value}%"
            }
        }
        result
    }






}
