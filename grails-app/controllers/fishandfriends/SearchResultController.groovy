package fishandfriends

import fishandfriends.exception.ServiceNotFoundException

/**
 * Search result Controller.
 */
class SearchResultController {

    /** The search facade object. */
    def searchFacadeService

    /**
     * List the query's first page.
     * @return
     */
    def index() {
        params.page = 0
        renderResults(params)
    }

    /**
     * Go to previous page.
     * @return
     */
    def prevPage() {
        params.page = Integer.parseInt(params.page) - 1
        renderResults(params)
    }

    /**
     * Go to next page.
     * @return
     */
    def nextPage() {
        params.page = Integer.parseInt(params.page) + 1
        renderResults(params)
    }

    /**
     * Execute the query and render the page.
     * @param p params.
     * @return
     */
    def renderResults(def p) {
        try {
            SearchResultObject resultObject = searchFacadeService.search(p.page, p.want, p.query)
            return render(view: "index", model: [page: params.page, want: params.want, query: params.query, result: resultObject.result, hasMore: resultObject.hasMore])
        }catch (ServiceNotFoundException e){
            return render(view: "index", model: [error:true])
        }
    }
}
