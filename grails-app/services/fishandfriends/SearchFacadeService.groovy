package fishandfriends

import grails.transaction.Transactional

@Transactional
class SearchFacadeService {

    /** Result per page */
    private final static int MAX_RESULT_PER_PAGE = 25;

    def fishingAreaService

    public SearchResultObject search(int page, String qType, String query) {
        // Compute parameters
        int max = MAX_RESULT_PER_PAGE + 1
        int offset = page * MAX_RESULT_PER_PAGE
        ISearchService searchService

        // Retrieve objects
        if (qType == "fishingArea") {
            searchService = fishingAreaService
        } else {
            throw new RuntimeException("Unable to catch the service")
        }

        // Do search
        def result = searchService.search(max, offset, query)

        // Check if there is more elements and set the resultObject
        SearchResultObject searchResultObject = new SearchResultObject()
        searchResultObject.result = result.subList(0,Math.min(result.size(),MAX_RESULT_PER_PAGE))
        searchResultObject.hasMore = result.size() > MAX_RESULT_PER_PAGE

        return searchResultObject
    }
}
