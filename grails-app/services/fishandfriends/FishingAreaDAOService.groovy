package fishandfriends

import grails.transaction.Transactional

@Transactional
class FishingAreaDAOService {

    def saveFishingArea(FishingArea fishingArea){

        fishingArea.save(flush: true, failOnError: true)
    }
}
