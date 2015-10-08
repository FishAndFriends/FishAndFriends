package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingAreaService {


    def getFishingAreaByNameOrLocation(String value) {

        def criteria = FishingArea.createCriteria()

        List<FishingArea> result = criteria.listDistinct {
            or {
                ilike 'name', "%${value}%"
                ilike 'location',"%${value}%"
            }
        }
        result
    }



}
