package fishandfriends
/**
 * Controller of the FishingMan.
 */
class FishingManController {
    /** Service to set and get information on a FishingMan in database. */
    FishingManService fishingManService

    /** Service to get Catches */
    CatchService catchService

    /**
     * Service to get information of fishing areas.
     */
    FishingAreaService fishingAreaService

    /**
     * Service to compute score of the Fishing Man.
     */
    def scoreService

    /**
     * Return default page view of the Fishing Man <i>fishingManInstance</i>.
     *
     * @param fishingManInstance
     * @return View of the Fishing Man.
     */
    def show(FishingMan fishingManInstance) {
        def catchList = catchService.getCatchesByFishingMan(fishingManInstance)
        def score = scoreService.computeScoresForFishingMan(fishingManInstance)
        def fishingArea = fishingAreaService.getFishingAreaByFishingMan(fishingManInstance)
        render(view: "show", model: [fishingManInstance: fishingManInstance, catches: catchList, score: score, fishingAreas: fishingArea])
    }

    /**
     * Return FishingMan <i>fishingManInstance</i> to modify.
     *
     * @param fishingManInstance FishingMan to modify.
     * @return FishingMan
     */
    def edit(FishingMan fishingManInstance) {
        if (!fishingManInstance.id.equals(request.session.fishingMan.id)) {
            redirect(action: "show", id: fishingManInstance.id)
        }

        def model = [:]
        model['fishingManInstance'] = fishingManInstance

        if (flash.containsKey('errors')) {
            model['errors'] = flash.errors
        }

        if (flash.containsKey('fishingMan')) {
            model['fishingManInstance'] = flash.fishingMan
        }

        render(view: "edit", model: model)
    }

    /**
     * Return FishingMan <i>fishingManInstance</i> to edit.
     *
     * @param fishingManInstance FishingMan to edit.
     * @return FishingMan.
     */
    def editProfile(FishingMan fishingManInstance) {
        if (!fishingManInstance.id.equals(request.session.fishingMan.id)) {
            redirect(action: "show", id: fishingManInstance.id)
            return
        }

        fishingManInstance.firstname = params.firstnameEdit
        fishingManInstance.lastname = params.lastnameEdit

        fishingManService.insertOrUpdateFishingMan(fishingManInstance)

        if (fishingManInstance.hasErrors()) {
            flash.fishingMan = fishingManInstance
            redirect action: "edit", id: fishingManInstance.id
        } else {
            redirect action: "show", id: fishingManInstance.id
        }
    }

    /**
     * Return view to edit password of the FishingMan <i>fishingManInstance</i>.
     *
     * @param fishingManInstance FishingMan who want modify it password.
     * @return Edit password view.
     */
    def editPassword(FishingMan fishingManInstance) {
        if (!fishingManInstance.id.equals(request.session.fishingMan.id)) {
            redirect(action: "show", id: fishingManInstance.id)
            return
        }

        def errors = []

        if (fishingManService.controlPassword(fishingManInstance, params.oldPassword)) {
            fishingManInstance.tmpPassword = params.newPassword
            fishingManService.insertOrUpdateFishingMan(fishingManInstance)
        } else {
            errors.add(message(code: "fishandfriends.fishingMan.wrongOldPassword"))
        }

        flash.errors = errors
        if (fishingManInstance.hasErrors() || !errors.isEmpty()) {
            flash.fishingMan = fishingManInstance
            redirect action: "edit", id: fishingManInstance.id
        } else {
            redirect action: "show", id: fishingManInstance.id
        }
    }

    /**
     * Return view to share a Catch by the FishingMan <i>fishingManInstance</i>.
     *
     * @param fishingManInstance Fishing Man who share a Catch.
     * @return Catch share view.
     */
    def shareCatch(FishingMan fishingManInstance) {
        render(view: "shareCatch", model: [fishingManInstance: fishingManInstance])
    }

    /**
     * Method used when a FishingMan <i>fishingManInstance</i> want to share a
     * <b>Catch</b>. A catch concerns a fish (name, size and weight) taken in a
     * location given.
     * @param fishingManInstance FishingMan who share a <b>Catch</b>.
     * @return a catch
     */
    def shareCatchLocation() {

        if (session.fishingMan == null) {
            render(view: "shareCatch",
                    model: [fishingManInstance: session.fishingMan])
        } else {
            if (params.fishingAreaNameShared || params.fishNameShared
                    || params.fishWeightShared || params.fishSizeShared) {
                Catch aCatch = new Catch(
                        date: new Date(),
                        fishingMan: session.fishingMan,
                        fishingArea: params.fishingAreaNameShared,
                        fish: params.fishNameShared,
                        weight: params.fishWeightShared,
                        size: params.fishSizeShared,
                )

                if (aCatch.fishingMan != null && aCatch.fishingArea != null
                        && aCatch.fish != null
                        && aCatch.weight > 0.0 && aCatch.size > 0.0) {
                    aCatch.save(flush: true)
                    redirect view: "index", controller: "login"
                } else {
                    render(view: "shareCatch",
                            model: [fishingManInstance: session.fishingMan])
                }

            }
        }
    }
}
