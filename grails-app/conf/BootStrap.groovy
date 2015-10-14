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
        FishingMan fishingMan = new FishingMan(firstname: "Jean-Michel", email: "jm@yahoo.fr", hashedPassword: "mypassword", saltedPassword: "mypassword", lastname: "Dupont", gender: "H")
        fishingManList << fishingMan
        fishingManList << new FishingMan(firstname: "Jaqueline", email: "jaja@yahoo.fr", hashedPassword: "miam", saltedPassword: "miam", lastname: "Dupont", gender: "F")

        // FishingArea
        FishingArea fishingArea = new FishingArea(location: "Toulouse", name: "L'étan du petit village")
        fishingAreaList << fishingArea
        fishingAreaList << new FishingArea(location: "Toutouland", name: "Pêche de chiens")

        // Comment
        commentList << new Comment(fishingMan: fishingMan, commentable: fishingArea)


        //Fish
        fishList << new Fish(name: "Aligator",weightAverage: 10.2, sizeAverage: 10.0)
        fishList << new Fish(name: "anguille",weightAverage: 7.2, sizeAverage: 100)
        fishList << new Fish(name: "Requin blanc",weightAverage: 1500.0, sizeAverage: 400)
        fishList << new Fish(name: "Requin taureau",weightAverage: 999.0, sizeAverage: 250)
        fishList << new Fish(name: "Nemo", weightAverage: 2, sizeAverage: 5)
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
