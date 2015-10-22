package fishandfriends

import grails.transaction.Transactional

@Transactional(readOnly = true)
class FishingAreaController {

    CatchService catchService
    def fishingAreaDAOService
    def scoreService
    def noteService

    def show(FishingArea fishingAreaInstance) {
        def catchList = catchService.getCatchesWithNbCommentsByFishingArea(fishingAreaInstance)
        def score = scoreService.computeScoresForFishingArea(fishingAreaInstance)

        render(view: "show", model: [fishingAreaInstance: fishingAreaInstance, catches: catchList, score: score])
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
}
