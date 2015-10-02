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

    def create() {
        respond new FishingMan(params)
    }

    @Transactional
    def save(FishingMan fishingManInstance) {
        if (fishingManInstance == null) {
            notFound()
            return
        }

        if (fishingManInstance.hasErrors()) {
            respond fishingManInstance.errors, view: 'create'
            return
        }

        fishingManInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fishingMan.label', default: 'FishingMan'), fishingManInstance.id])
                redirect fishingManInstance
            }
            '*' { respond fishingManInstance, [status: CREATED] }
        }
    }

    def edit(FishingMan fishingManInstance) {
        respond fishingManInstance
    }

    @Transactional
    def update(FishingMan fishingManInstance) {
        if (fishingManInstance == null) {
            notFound()
            return
        }

        if (fishingManInstance.hasErrors()) {
            respond fishingManInstance.errors, view: 'edit'
            return
        }

        fishingManInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FishingMan.label', default: 'FishingMan'), fishingManInstance.id])
                redirect fishingManInstance
            }
            '*' { respond fishingManInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FishingMan fishingManInstance) {

        if (fishingManInstance == null) {
            notFound()
            return
        }

        fishingManInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FishingMan.label', default: 'FishingMan'), fishingManInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
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


    def inscriptionValidation(){
        FishingMan fishingMan = new FishingMan(firstname:params.signupFirstname,lastname:params.signupLastname,email:params.signupMail,
                            password:signupPwd,gender:params.radioGender)
        def result = fishingMan.validate()
        if(result == true){
            fishingManService.insertOrUpdateFishingMan(fishingMan)
        } else {
            params.error = ""
        }
    }





}
