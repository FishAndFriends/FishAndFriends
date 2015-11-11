package fishandfriends

import com.megatome.grails.RecaptchaService

/**
 * Controller of Login.
 */
class LoginController {
    /** Service of a <b>FishingManS</b>. */
    FishingManService fishingManService
    RecaptchaService recaptchaService
    CatchService catchService
    /**
     * Return the login ("index") page if user is not connected ; otherwise return
     * "newsfeed" page of the user.
     * @return correct index page.
     */
    def index() {
        if (session.fishingMan != null) {
            def catchList = catchService.getCatchesWithNbCommentsForNewsfeed(session.fishingMan)
            render(view: "newsfeed", model: [catches: catchList])
        } else {
            def model = [:]
            if (flash.containsKey('fishingMan')) {
                model['fishingManInstance'] = flash.fishingMan
            }

            if (flash.containsKey('errors')) {
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
    def inscription() {
        def recaptchaOK = true
        def errors = []
        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            recaptchaOK = false
            errors.add(message(code: "fishandfriends.login.robot"))
            flash.errors = errors
        }

        FishingMan fishingMan = new FishingMan(firstname: params.signupFirstname,
                lastname: params.signupLastname, email: params.signupMail,
                gender: params.radioGender, tmpPassword: params.signupPwd)

        if (recaptchaOK) {
            fishingManService.insertOrUpdateFishingMan(fishingMan)

            if (fishingMan.id != null) {
                session.fishingMan = fishingMan
            } else {
                session.fishingMan = null
            }
        }

        flash.fishingMan = fishingMan
        redirect(view: "index", params: params)
    }

    /**
     * Return index page when a user try to sign in.
     * Check if email address and password is valid.
     * @return index page.
     */
    def connexion() {
        def errors = []

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
