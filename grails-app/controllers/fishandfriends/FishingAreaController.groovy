package fishandfriends

/**
 * Controller of FishingArea.
 */
class FishingAreaController {
    /** Service to get catches from data. */
    CatchService catchService

    /** Service of subscription for Fishing Area. */
    def fishingAreaDAOService

    /** Service to get a set of information according Fishing Area existing */
    def fishingAreaService

    /** Service of computing scores */
    def scoreService

    /** Service of attributing note */
    def noteService

    /**
     * Return default page view of the Fishing Area <i>fishingAreaInstance</i>.
     *
     * @param fishingAreaInstance
     * @return View of the Fishing Area.
     */
    def show(FishingArea fishingAreaInstance) {
        def catchList = catchService.getCatchesWithNbCommentsByFishingArea(fishingAreaInstance)
        def score = scoreService.computeScoresForFishingArea(fishingAreaInstance)
        def isAlreadySuscribing = fishingAreaService.isSuscriberToArea(fishingAreaInstance,session.fishingMan)
        def noteGiven = noteService.getNoteGivenByAFishingMan(session.fishingMan, fishingAreaInstance)

        render(view: "show", model: [fishingAreaInstance: fishingAreaInstance, catches: catchList, score: score, noteGiven: noteGiven, isAlreadySuscribing : isAlreadySuscribing])
    }

    /**
     * Return default page view of the Fishing Area which comes to be created.
     *
     * @return View of the Fishing Area.
     */
    def createFishingArea() {
        FishingArea fishingArea =
                new FishingArea(
                        name: params.fishingAreaName,
                        location: params.fishingAreaLocation,
                        description: params.fishingAreaDescription)
        fishingArea.addToFishingMen(session.fishingMan)
        fishingAreaDAOService.saveFishingArea(fishingArea)

        redirect(controller: "fishingArea", action: "show", id: fishingArea.id)
    }

    /**
     * Return view to add a new Fishing Area by the Fishing Man
     * <i>fishingManInstance</i>.
     *
     * @param fishingManInstance FishingMan who want to create a new area.
     * @return View to create a new Area.
     */
    def addNewArea(FishingMan fishingManInstance) {
        render(view: "create", model: [fishingManInstance: fishingManInstance])
    }

    /**
     * Return main view of the Fishing area after it has been attributed of a
     * note.
     *
     * @return View of the Fishing area.
     */
    def note() {
        noteService.insertOrUpdate(
                session.fishingMan,
                FishingArea.findById(
                        params.fishingArea), Integer.parseInt(params.note))

        redirect(action: 'show', id: params.fishingArea)
    }

    /**
     * Return main view of the Fishing area <i>fishingAreaInstance</i> after
     * that it has been subscribed.
     *
     * @param fishingAreaInstance Fishing area subscribed by the user.
     * @return View of the Fishing area.
     */
    def suscribeUnsuscribeToArea(FishingArea fishingAreaInstance) {
        fishingAreaDAOService.suscribeUnsuscribeToArea(
                fishingAreaInstance, session.fishingMan)
        redirect(action: "show", id: fishingAreaInstance.id)
    }
}
