package fishandfriends

import grails.transaction.Transactional

@Transactional
class CatchService {

    /**
     * Insert or update the Catch define by <i>aCatch</i>.
     * @param aCatch Catch to save into database.
     * @return Catch stored.
     */
    Catch insertOrUpdateCatch(Catch aCatch) {
        aCatch.save(flush: true)
    }

}
