package fishandfriends


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FishingManController {
    FishingManService fishingManService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FishingMan.list(params), model: [fishingManInstanceCount: FishingMan.count()]
    }

    def show(FishingMan fishingManInstance) {
        respond fishingManInstance
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
        def password = fishingManInstance.saltedPassword + params.oldPassword
        password = password.encodeAsSHA1()

        if (fishingManInstance.hashedPassword.equals(password)) {
            fishingManInstance.hashedPassword =
                    (fishingManInstance.saltedPassword + params.newPassword).encodeAsSHA1()
            fishingManService.insertOrUpdateFishingMan(fishingManInstance)
            redirect action: "show", id: fishingManInstance.id
        } else {
            errors.add(message(code: "fishandfriends.fishingMan.userNotFound"))
        }

    }

    def shareCatch(FishingMan fishingManInstance) {
        render(view: "shareCatch", model:[fishingManInstance: fishingManInstance])
    }

    def shareCatchLocation(FishingMan fishingManInstance) {
        if (fishingManInstance == null) {
            notFound()
            return
        }

        if (fishingManInstance.hasErrors()) {
            render( view: "shareCatch",
                    model:[fishingManInstance: fishingManInstance])
        }

        Catch aCatch = null
        if (params.fishingAreaNameShared || params.fishNameShared
            || params.fishWeightShared || params.fishSizeShared) {
            aCatch = new Catch(
                    date: new Date(),
                    fishingMan: fishingManInstance,
                    fishingArea: params.fishingAreaNameShared,
                    fish: params.fishNameShared,
                    weight: params.fishWeightShared,
                    size: params.fishSizeShared,
            )

        }
        aCatch.insertO
    }
}
