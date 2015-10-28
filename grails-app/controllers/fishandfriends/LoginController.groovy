package fishandfriends

class LoginController {
    FishingManService fishingManService

    def index() {
        if (session.fishingMan != null) {
            // Quand on aura fait un controller dédié
            // redirect(controller:"newsfeed",action:"index")
            render(view: "newsfeed")
        } else {
            def model = [:]
            if (flash.containsKey('fishingMan')) {
                model['fishingManInstance'] = flash.fishingMan
            }

            if (flash.containsKey('errors')){
                model['errors'] = flash.errors
            }

            render(view: "index", model: model)
        }
    }

    def inscription(){
        FishingMan fishingMan = new FishingMan(firstname:params.signupFirstname,lastname:params.signupLastname,email:params.signupMail,
                gender:params.radioGender, tmpPassword: params.signupPwd)

        fishingManService.insertOrUpdateFishingMan(fishingMan)

        if (fishingMan.id != null) {
            session.fishingMan = fishingMan
        }
        flash.fishingMan = fishingMan
        redirect(view: "index")
    }

    def connexion() {
        def errors= []

        if (!params.connectMail) {
            errors.add(message(code: "fishandfriends.fishingMan.requiredMail"))
        }

        if (!params.connectPwd) {
            errors.add(message(code: "fishandfriends.fishingMan.requiredPwd"))
        }

        if (errors.isEmpty()) {
            FishingMan fishingMan = fishingManService.findByEmail(params.connectMail)
            if (fishingManService.controlPassword(fishingMan, params.connectPwd)) {
                session.fishingMan = fishingMan
            } else {
                errors.add(message(code: "fishandfriends.fishingMan.userNotFound"))
            }
        }

        flash.errors = errors
        redirect(view: "index")
    }

    def deconnexion() {
        session.removeAttribute("fishingMan")
        redirect(view: "index")
    }
}
