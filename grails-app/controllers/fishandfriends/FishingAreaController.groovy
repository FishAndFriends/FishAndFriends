package fishandfriends

class FishingAreaController {

    CatchService catchService
    def fishingAreaDAOService
    def fishingAreaService
    def scoreService
    def noteService

    def show(FishingArea fishingAreaInstance) {
        def catchList = catchService.getCatchesWithNbCommentsByFishingArea(fishingAreaInstance)
        def score = scoreService.computeScoresForFishingArea(fishingAreaInstance)
        def isAlreadySuscribing = fishingAreaService.isSuscriberToArea(fishingAreaInstance,session.fishingMan)
        def noteGiven = noteService.getNoteGivenByAFishingMan(session.fishingMan, fishingAreaInstance)
        render(view: "show", model: [fishingAreaInstance: fishingAreaInstance, catches: catchList, score: score, noteGiven: noteGiven, isAlreadySuscribing : isAlreadySuscribing])
    }

    def doSearchFishingArea() {
        def fishingAreaList = fishingAreaService.getFishingAreaByNameOrLocation(params.placeHolder)
        fishingAreaList.sort()
        render(view: 'index', model: [fishingAreaInstanceList: fishingAreaList, fishingAreaInstanceCount: fishingAreaList.size()])

    }

    def createFishingArea() {
        FishingArea fishingArea = new FishingArea(name: params.fishingAreaName,
                location: params.fishingAreaLocation, description: params.fishingAreaDescription)
        fishingArea.addToFishingMen(session.fishingMan)
        fishingAreaDAOService.saveFishingArea(fishingArea)
        redirect(controller: "login", view: "newsfeed")
    }

    def addNewArea(FishingMan fishingManInstance) {
        render(view: "create", model: [fishingManInstance: fishingManInstance])
    }

    def note() {
        noteService.insertOrUpdate(session.fishingMan, FishingArea.findById(params.fishingArea), Integer.parseInt(params.note))

        redirect(action: 'show', id: params.fishingArea)
    }
    def suscribeUnsuscribeToArea(FishingArea fishingAreaInstance) {
        fishingAreaDAOService.suscribeUnsuscribeToArea(fishingAreaInstance,session.fishingMan)
        redirect(action: "show", id: fishingAreaInstance.id)
    }
}
