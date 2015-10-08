package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingManService {

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

}
