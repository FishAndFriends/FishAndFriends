import fishandfriends.Comment
import fishandfriends.FishingArea
import fishandfriends.FishingMan

class BootStrap {

    def init = { servletContext ->
        // FishingMan
        FishingMan fishingMan = new FishingMan(firstname: "Jean-Michel", email: "jm@yahoo.fr", password: "mypassword", lastname: "Dupont", gender: "H")
        FishingMan fishingMan2 = new FishingMan(firstname: "Jaqueline", email: "jaja@yahoo.fr", password: "miam", lastname: "Dupont", gender: "F")

        // FishingArea
        FishingArea fishingArea = new FishingArea(location: "Toulouse", name: "L'étan du petit village")
        FishingArea fishingArea2 = new FishingArea(location: "Toutouland", name: "Pêche de chiens")

        // Comment
        Comment comment = new Comment(fishingMan: fishingMan, commentable: fishingArea)

        // Save them all !
        fishingMan.save(flush: true)
        fishingMan2.save(flush: true)

        fishingArea2.save(flush: true)
        fishingArea.save(flush: true)

        comment.save(flush: true)
    }
    def destroy = {
    }
}
