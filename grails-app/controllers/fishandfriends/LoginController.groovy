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
            render(view: "index")
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

        redirect(view: "index", model: [fishingManInstance: fishingMan])
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

        redirect(view: "index", model: [errors: errors])
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
