package fishandfriends

class SearchResultController {

    def searchFacadeService

    def index() {
        params.page = 0
        renderResults(params)
    }

    def prevPage() {
        params.page = Integer.parseInt(params.page) - 1
        renderResults(params)
    }

    def nextPage() {
        params.page = Integer.parseInt(params.page) + 1
        renderResults(params)
    }

    def renderResults(def p) {
        try {
            SearchResultObject resultObject = searchFacadeService.search(p.page, p.want, p.query)
            return render(view: "index", model: [page: params.page, want: params.want, query: params.query, result: resultObject.result, hasMore: resultObject.hasMore])
        }catch (RuntimeException e){
            return render(view: "index", model: [error:true])
        }
    }
}
