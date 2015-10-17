package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingManService implements ISearchService {

    /**
     * insertOrUpdateFishingMan
     * @param the fishingMan to insert or update
     * @return the fishingMan just inserted
     */
    FishingMan insertOrUpdateFishingMan(FishingMan fishingMan){
        fishingMan.save(flush:true)
    }

    FishingMan findByEmail(String mail) {
        return FishingMan.findByEmail(mail)
    }

    @Override
    def search(int max, int offset, String query) {
        def criteria = FishingMan.createCriteria()

        List<FishingMan> result = criteria.list(max: max, offset: offset) {
            or {
                ilike 'lastname', "%${query}%"
                ilike 'firstname',"%${query}%"
            }
        }
        result
    }
}

