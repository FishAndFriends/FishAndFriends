import fishandfriends.Comment
import fishandfriends.FishingArea
import fishandfriends.FishingMan

class BootStrap {

    def fishingManList = []
    def fishingAreaList = []
    def commentList = []

    def init = { servletContext ->
        // FishingMan
        FishingMan fishingMan = new FishingMan(firstname: "Jean-Michel", email: "jm@yahoo.fr", password: "mypassword", lastname: "Dupont", gender: "H")
        fishingManList << fishingMan
        fishingManList << new FishingMan(firstname: "Jaqueline", email: "jaja@yahoo.fr", password: "miam", lastname: "Dupont", gender: "F")

        // FishingArea
        FishingArea fishingArea = new FishingArea(location: "Toulouse", name: "L'étan du petit village")
        fishingAreaList << fishingArea
        fishingAreaList << new FishingArea(location: "Toutouland", name: "Pêche de chiens")

        // Comment
        commentList << new Comment(fishingMan: fishingMan, commentable: fishingArea)

        // Save them all !
        fishingManList.each {
            it.save(flush: true)
        }
        fishingAreaList.each {
            it.save(flush: true)
        }
        commentList.each {
            it.save(flush: true)
        }
    }
    def destroy = {
        // Release them all !
        fishingManList.each {
            it.delete(flush: true)
        }
        fishingAreaList.each {
            it.delete(flush: true)
        }
        commentList.each {
            it.delete(flush: true)
        }
    }
}
