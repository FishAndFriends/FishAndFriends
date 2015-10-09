import fishandfriends.Comment
import fishandfriends.FishingArea
import fishandfriends.FishingMan
import fishandfriends.Fish

class BootStrap {

    def fishingManList = []
    def fishingAreaList = []
    def fishList = []
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

        //Fish
        Fish carpe = new Fish(name: "Carpe",weightAwerage: 0.5, sizeAverage: 10.0)
        Fish brochet = new Fish(name: "Brochet",weightAwerage: 7.2, sizeAverage: 100)
        Fish requinBlanc = new Fish(name: "Requin blanc",weightAwerage: 1500, sizeAverage: 400)
        Fish requinTaureau = new Fish(name: "Requin taureau",weightAwerage: 999, sizeAverage: 250)
        fishList << carpe
        fishList << brochet
        fishList << requinBlanc
        fishList << requinTaureau

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
        fishList.each {
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
        fishList.each {
            it.delete(flush: true)
        }
    }
}
