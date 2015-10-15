package fishandfriends


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FishingAreaController {

    CatchService catchService
    def fishingAreaDAOService
    def scoreService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FishingArea.list(params), model: [fishingAreaInstanceCount: FishingArea.count()]
    }

    def show(FishingArea fishingAreaInstance) {
        def catchList = catchService.getCatchesByFishingArea(fishingAreaInstance)
        def score = scoreService.computeScoresForFishingArea(fishingAreaInstance)
        render(view: "show", model:[fishingAreaInstance: fishingAreaInstance,catches:catchList, score:score])
    }

    def create() {
        respond new FishingArea(params)
    }

    @Transactional
    def save(FishingArea fishingAreaInstance) {
        if (fishingAreaInstance == null) {
            notFound()
            return
        }

        if (fishingAreaInstance.hasErrors()) {
            respond fishingAreaInstance.errors, view: 'create'
            return
        }

        fishingAreaInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fishingArea.label', default: 'FishingArea'), fishingAreaInstance.id])
                redirect fishingAreaInstance
            }
            '*' { respond fishingAreaInstance, [status: CREATED] }
        }
    }

    def edit(FishingArea fishingAreaInstance) {
        respond fishingAreaInstance
    }

    @Transactional
    def update(FishingArea fishingAreaInstance) {
        if (fishingAreaInstance == null) {
            notFound()
            return
        }

        if (fishingAreaInstance.hasErrors()) {
            respond fishingAreaInstance.errors, view: 'edit'
            return
        }

        fishingAreaInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FishingArea.label', default: 'FishingArea'), fishingAreaInstance.id])
                redirect fishingAreaInstance
            }
            '*' { respond fishingAreaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(FishingArea fishingAreaInstance) {

        if (fishingAreaInstance == null) {
            notFound()
            return
        }

        fishingAreaInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FishingArea.label', default: 'FishingArea'), fishingAreaInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fishingArea.label', default: 'FishingArea'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def doSearchFishingArea() {
        def fishingAreaList = fishingAreaService.getFishingAreaByNameOrLocation(params.placeHolder)
        fishingAreaList.sort()
        render(view: 'index', model: [fishingAreaInstanceList: fishingAreaList, fishingAreaInstanceCount: fishingAreaList.size()])

    }

    def createFishingArea() {
        FishingArea fishingArea = new FishingArea(params)
        fishingAreaDAOService.saveFishingArea(fishingArea)

        redirect action: "index"
    }
    

    }
