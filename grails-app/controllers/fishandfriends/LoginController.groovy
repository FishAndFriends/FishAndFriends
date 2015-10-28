package fishandfriends

class LoginController {
    /** Service of a <b>FishingManS</b>. */
    FishingManService fishingManService

    /**
     * Return the login ("index") page if user is not connected ; otherwise return
     * "newsfeed" page of the user.
     * @return correct index page.
     */
    def index() {
        if (session.fishingMan != null) {
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

    /**
     * Return index page after to have sign up of a fishingMan with information
     * entered by the user.
     * @return index page of the user connected.
     */
    def inscription(){
        FishingMan fishingMan = new FishingMan(firstname:params.signupFirstname,
                lastname:params.signupLastname,email:params.signupMail,
                gender:params.radioGender, tmpPassword: params.signupPwd)

        fishingManService.insertOrUpdateFishingMan(fishingMan)

        if (fishingMan.id != null) {
            session.fishingMan = fishingMan
        }
        else {
            session.fishingMan = null
        }

        flash.fishingMan = fishingMan
        redirect(view: "index")
    }

    /**
     * Return index page when a user try to sign in.
     * Check if email address and password is valid.
     * @return index page.
     */
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

    /**
     * Return index page after disconnecting the user.
     * @return index page
     */
    def deconnexion() {
        session.removeAttribute("fishingMan")
        redirect(view: "index")
    }
}
