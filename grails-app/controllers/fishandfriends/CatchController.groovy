package fishandfriends
/**
 * Controller of the catch.
 */
class CatchController {
    FishingAreaService fishingAreaService

    /**
     * Return view to share a Catch by the FishingMan from a given FishingArea
     *
     * @param fishingArea The location to pick.
     * @return Catch share view.
     */
    def shareFromArea(FishingArea fishingArea) {
        flash.fishingArea = fishingArea
        redirect(action: 'share')
    }

    /**
     * Return view to share a Catch by the FishingMan.
     *
     * @return Catch share view.
     */
    def share() {
        def model = [:]
        model['fishList'] = Fish.list()
        model['fishingAreaList'] = fishingAreaService.getFishingAreaByFishingMan(session.fishingMan)

        if (flash.containsKey('fishingArea')) {
            model['fishingArea'] = flash.fishingArea
        }

        if (flash.containsKey('catchInstance')) {
            model['catchInstance'] = flash.catchInstance
        } else {
            model['catchInstance'] = new Catch(params)
        }

        render(view: "share", model: model)
    }

    /**
     *  Method used to save the catch.
     *
     * @param catchInstance Instanceof the catch
     * @return The share catch view if it has errors. Else it goes back to the newsfeed
     */
    def save(Catch catchInstance) {
        catchInstance.date = new Date()
        catchInstance.fishingMan = session.fishingMan
        catchInstance.validate()

        if (catchInstance.hasErrors()) {
            flash.catchInstance = catchInstance
            redirect(action: 'share')
            return
        }

        catchInstance.save(flush: true)

        redirect(controller: 'login', action: 'index')
    }

}
