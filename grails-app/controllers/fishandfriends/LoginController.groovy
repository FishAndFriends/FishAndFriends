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

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[ nextInt( alphabet.length() ) ] }.join()
        }
    }

    def inscription(){
        FishingMan fishingMan = new FishingMan(firstname:params.signupFirstname,lastname:params.signupLastname,email:params.signupMail,
                gender:params.radioGender)

        // http://stackoverflow.com/a/8138604
        fishingMan.saltedPassword = generator((('A'..'Z')+('0'..'9')).join(), 9)
        fishingMan.hashedPassword = (fishingMan.saltedPassword + params.signupPwd).encodeAsSHA1()
        fishingManService.insertOrUpdateFishingMan(fishingMan)

        if (fishingMan.id != null) {
            session.fishingMan = fishingMan
        }

        render(view: "index", model: [fishingManInstance: fishingMan])
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
            if (fishingMan) {
                def password = fishingMan.saltedPassword + params.connectPwd
                password = password.encodeAsSHA1()

                if (fishingMan.hashedPassword.equals(password)) {
                    session.fishingMan = fishingMan
                } else {
                    errors.add(message(code: "fishandfriends.fishingMan.userNotFound"))
                }
            } else {
                errors.add(message(code: "fishandfriends.fishingMan.userNotFound"))
            }
        }

        if (session.fishingMan) {
            redirect(uri:'/')
        } else {
            render(view: "index", model: [errors: errors])
        }
    }

    def deconnexion() {
        session.removeAttribute("fishingMan")
        redirect(uri:'/')
    }
}
