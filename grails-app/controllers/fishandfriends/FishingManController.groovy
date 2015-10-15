package fishandfriends


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FishingManController {
    FishingManService fishingManService
    CatchService catchService


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FishingMan.list(params), model: [fishingManInstanceCount: FishingMan.count()]
    }

    def show(FishingMan fishingManInstance) {

        def catchList = catchService.getCatchesByFishingMan(fishingManInstance)
        render(view: "show", model:[fishingManInstance: fishingManInstance,catches:catchList])
        //respond fishingManInstance
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
            render(view: "edit", model:[fishingManInstance: fishingManInstance])
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
            render(view: "edit", model:[fishingManInstance: fishingManInstance])
        }

        def errors= []

        if (fishingManService.controlPassword(fishingManInstance, params.oldPassword)) {
            fishingManService.insertOrUpdateFishingMan(fishingManInstance)
            redirect action: "show", id: fishingManInstance.id
        } else {
            errors.add(message(code: "fishandfriends.fishingMan.userNotFound"))
        }

        render(view: "edit", model:[fishingManInstance: fishingManInstance])
    }
}
