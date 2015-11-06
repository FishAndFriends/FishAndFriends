package fishandfriends

import grails.transaction.Transactional

/**
 * Service to do search information in the database according text entered by
 * the FishingMan connected.
 */
@Transactional
class SearchFacadeService {

    /** Result per page. */
    private final static int MAX_RESULT_PER_PAGE = 25;

    /** Search service for fishingAreas. */
    def fishingAreaService
    /** Search service for fishes. */
    def fishService
    /** Search service for fishingMen. */
    def fishingManService

    /**
     * Retrieve all objects according to the query.
     * @param page the current page wanted.
     * @param qType the type of the search. Basically, it's 'fishingArea' or 'fish' or 'fishingMan'.
     * @param query the query.
     * @return the result of the query.
     */
    public SearchResultObject search(int page, String qType, String query) {
        // Compute parameters
        int max = MAX_RESULT_PER_PAGE + 1
        int offset = page * MAX_RESULT_PER_PAGE
        ISearchService searchService

        // Retrieve objects
        if (qType == "fishingArea") {
            searchService = fishingAreaService
        } else if (qType == "fish") {
            searchService = fishService
        } else if (qType == "fishingMan") {
            searchService = fishingManService
        } else {
            throw new RuntimeException("Unable to catch the service")
        }

        // Do search
        def result = searchService.search(max, offset, query)

        // Check if there is more elements and set the resultObject
        SearchResultObject searchResultObject = new SearchResultObject()
        searchResultObject.result = result.subList(0, Math.min(result.size(), MAX_RESULT_PER_PAGE))
        searchResultObject.hasMore = result.size() > MAX_RESULT_PER_PAGE

        return searchResultObject
    }
}
