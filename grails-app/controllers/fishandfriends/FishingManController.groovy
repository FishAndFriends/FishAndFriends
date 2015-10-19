package fishandfriends


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FishingManController {
    FishingManService fishingManService
    CatchService catchService
    def scoreService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FishingMan.list(params), model: [fishingManInstanceCount: FishingMan.count()]
    }

    def show(FishingMan fishingManInstance) {
        def catchList = catchService.getCatchesByFishingMan(fishingManInstance)
        def score = scoreService.computeScoresForFishingMan(fishingManInstance)
        render(view: "show", model: [fishingManInstance: fishingManInstance, catches: catchList, score: score])
    }

    def edit(FishingMan fishingManInstance) {
        respond fishingManInstance
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fishingMan.label', default: 'FishingMan'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def editProfile(FishingMan fishingManInstance) {
        if (fishingManInstance == null) {
            notFound()
            return
        }

        if (fishingManInstance.hasErrors()) {
            render(view: "edit", model: [fishingManInstance: fishingManInstance])
        }

        if (params.firstnameEdit) {
            fishingManInstance.firstname = params.firstnameEdit
        }

        if (params.lastnameEdit) {
            fishingManInstance.lastname = params.lastnameEdit
        }

        fishingManService.insertOrUpdateFishingMan(fishingManInstance)
        redirect action: "show", id: fishingManInstance.id
    }

    def editPassword(FishingMan fishingManInstance) {
        if (fishingManInstance == null) {
            notFound()
            return
        }

        if (fishingManInstance.hasErrors()) {
            render(view: "edit", model: [fishingManInstance: fishingManInstance])
        }

        def errors = []

        if (fishingManService.controlPassword(fishingManInstance, params.oldPassword)) {
            fishingManService.insertOrUpdateFishingMan(fishingManInstance)
            redirect action: "show", id: fishingManInstance.id
        } else {
            errors.add(message(code: "fishandfriends.fishingMan.userNotFound"))
        }

        render(view: "edit", model: [fishingManInstance: fishingManInstance])
    }

    def shareCatch(FishingMan fishingManInstance) {
        render(view: "shareCatch", model:[fishingManInstance: fishingManInstance])
    }

    /**
     * Method used when a FishingMan <i>fishingManInstance</i> want to share a
     * <b>Catch</b>. A catch concerns a fish (name, size and weight) taken in a
     * location given.
     * @param fishingManInstance FishingMan who share a <b>Catch</b>.
     * @return a catch
     */
    def shareCatchLocation(FishingMan fishingManInstance) {
        if (fishingManInstance == null) {
            notFound()
            return
        }

        if (fishingManInstance.hasErrors()) {
            render( view: "shareCatch",
                    model:[fishingManInstance: fishingManInstance])
        }

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
            CatchService.insertOrUpdateCatch(aCatch)
            render(view: "login.newsfeed")
        }

    }

}
