package fishandfriends

import grails.transaction.Transactional

/**
 * Service of Fishing Area to get data on Fishing Areas which already exists
 * in database. It service is able to :
 *     - search in database a list of Fishing areas according to a name or a
 *     location
 *     - know if the area is subscribed by a Fishing Man
 *     - return list of subscriber Fishing Man for this area.
 */
@Transactional
class FishingAreaService implements ISearchService {

    /**
     * Return a list of Fishing Area existing in database according a
     * <i>query</i>. The <i>query</i> could be a name or location area.
     * <i>max</i> argument is used to define the number of maximum results to
     * display. <i>offset</i> specifies number of elements per page.
     *
     * @param max Maximum number of results
     * @param offset Maximum number of results per page.
     * @param query Name or location Area.
     * @return List of Fishing Areas which match with the <i>query</i>.
     */
    def search(int max, int offset, String query) {
        def criteria = FishingArea.createCriteria()

        List<FishingArea> result = criteria.list(max: max, offset: offset) {
            or {
                ilike 'name', "%${query}%"
                ilike 'location', "%${query}%"
            }
        }
        result
    }

    /**
     * Return TRUE is the Fishing Man <i>fishingMan</i> is a subscriber of the
     * Fishing Area <i>fishingAreaInstance</i> ; otherwise return FALSE.
     *
     * @param fishingAreaInstance Fishing area which is supposed subscribe by
     * the Fishing Man <i>fishingMan</i>.
     * @param fishingMan Fishing man who is supposed to be subscribed to the
     * Fishing Area <i>fishingAreaInstance</i>.
     * @return Boolean value.
     */
    def isSuscriberToArea(FishingArea fishingAreaInstance, FishingMan fishingMan) {
        fishingMan = FishingMan.findById(fishingMan.id)

        //Test already suscribed
        def isIn = FishingArea.createCriteria().get {
            idEq(fishingAreaInstance.id)

            fishingMen {
                idEq(fishingMan.id)

                projections {
                    count 'id'
                }
            }

        }

        return isIn > 0
    }

    /**
     * Return a list of Fishing Areas for a given Fishing Man
     * <i>afishingMan</i>.
     * @param afishingMan Fishing man who has subscribed to a list of Fishing
     * Areas.
     * @return List of Fishing Areas.
     */
    def getFishingAreaByFishingMan(FishingMan afishingMan) {
        def criteria = FishingArea.createCriteria()

        def result = criteria.list(fetch: [fishingMen: "eager"]) {
            fishingMen {
                idEq(afishingMan.id)
            }
        }
        result
    }
}
