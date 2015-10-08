package fishandfriends

/**
 * Created by gayout on 08/10/15.
 */
class FishService implements ISearchService{


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
