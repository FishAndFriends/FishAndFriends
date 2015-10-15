package fishandfriends

class LoginController {
    FishingManService fishingManService

    def index() {
        if (session.fishingMan != null) {
            // Quand on aura fait un controller dédié
            // redirect(controller:"newsfeed",action:"index")
            render(view: "newsfeed")
        } else {
            render(view: "index")
        }
    }

    def inscription(){
        FishingMan fishingMan = new FishingMan(firstname:params.signupFirstname,lastname:params.signupLastname,email:params.signupMail,
                gender:params.radioGender, tmpPassword: params.signupPwd)

        fishingManService.insertOrUpdateFishingMan(fishingMan)

        if (fishingMan.id != null) {
            session.fishingMan = fishingMan
        }

        redirect(view: "index", model: [fishingManInstance: fishingMan])
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

        redirect(view: "index", model: [errors: errors])
    }

    def deconnexion() {
        session.removeAttribute("fishingMan")
        redirect(view: "index")
    }
}
