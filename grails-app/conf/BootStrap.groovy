import fishandfriends.Comment
import fishandfriends.FishingArea
import fishandfriends.FishingMan
import fishandfriends.Fish

class BootStrap {

    def fishingManList = []
    def fishingAreaList = []
    def commentList = []
    def fishList = []

    def init = { servletContext ->
        // FishingMan
        FishingMan fishingMan = new FishingMan(firstname: "Jean-Michel", email: "jm@yahoo.fr", hashedPassword: "mypassword", saltedPassword: "mypassword", lastname: "Dupont", gender: "H")
        fishingManList << fishingMan
        fishingManList << new FishingMan(firstname: "Jaqueline", email: "jaja@yahoo.fr", hashedPassword: "miam", saltedPassword: "miam", lastname: "Dupont", gender: "F")

        // FishingArea
        FishingArea fishingArea = new FishingArea(location: "Toulouse", name: "L'étan du petit village")
        fishingAreaList << fishingArea
        fishingAreaList << new FishingArea(location: "Toutouland", name: "Pêche de chiens")

        // Comment
        commentList << new Comment(fishingMan: fishingMan, commentable: fishingArea)

        // Fish
        fishList << new Fish(name: "Nemo", weightAverage: 10, sizeAverage: 5)
        fishList << new Fish(name: "Poisson rouge", weightAverage: 10, sizeAverage: 5)
        fishList << new Fish(name: "Néon", weightAverage: 5, sizeAverage: 3)
        fishList << new Fish(name: "Combattant", weightAverage: 10, sizeAverage: 5)

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
