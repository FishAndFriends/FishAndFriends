package fishandfriends

/**
 * Service to find Fishes in the database.
 */
class FishService implements ISearchService {

    /**
     * Return a list of Fishing Area existing in database according a
     * <i>query</i>. The <i>query</i> could be a name of a Fish.
     * <i>max</i> argument is used to define the number of maximum results to
     * display.
     * <i>offset</i> specifies number of elements per page.
     *
     * @param max Maximum number of results
     * @param offset Maximum number of results per page.
     * @param query Name or location Area.
     * @return List of Fishing Areas which match with the <i>query</i>.
     */
    def search(int max, int offset, String query) {
        def criteria = Fish.createCriteria()

        List<Fish> result = criteria.list(max: max, offset: offset) {
            or {
                ilike 'name', "%${query}%"
            }
        }
        result
    }
}
