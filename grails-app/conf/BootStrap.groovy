import fishandfriends.*

@Transactional
class BootStrap {
    FishingManService fishingManService

    def fishingManList = []
    def fishingAreaList = []
    def fishList = []
    def catchList = []
    def commentList = []
    def noteList = []

    def init = { servletContext ->
        // FishingMan
        FishingMan fishingMan = new FishingMan(firstname: "Jean-Michel", email: "jm@yahoo.fr", tmpPassword: "password", lastname: "Dupont", gender: "H")
        fishingManList << fishingMan
        fishingManList << new FishingMan(firstname: "Jaqueline", email: "jaja@yahoo.fr", tmpPassword: "password", lastname: "Dupont", gender: "F")

        // FishingArea
        FishingArea fishingArea = new FishingArea(location: "Toulouse", name: "L'étan du petit village", fishingMen: fishingManList)
        fishingAreaList << fishingArea
        fishingAreaList << new FishingArea(location: "Toutouland", name: "Pêche de chiens")

        // Comment
        commentList << new Comment(fishingMan: fishingMan, commentable: fishingArea)


        //Fish
        fishList << new Fish(name: "Aligator", weightAverage: 10.2, sizeAverage: 10.0)
        fishList << new Fish(name: "anguille", weightAverage: 7.2, sizeAverage: 100)
        fishList << new Fish(name: "Requin blanc", weightAverage: 1500.0, sizeAverage: 400)
        fishList << new Fish(name: "Requin taureau", weightAverage: 999.0, sizeAverage: 250)
        fishList << new Fish(name: "Nemo", weightAverage: 2, sizeAverage: 5)
        fishList << new Fish(name: "Poisson rouge", weightAverage: 10, sizeAverage: 5)
        fishList << new Fish(name: "Néon", weightAverage: 5, sizeAverage: 3)
        fishList << new Fish(name: "Combattant", weightAverage: 10, sizeAverage: 5)


        //Catch
        catchList << new Catch(date: new Date(year: 2002,month: 10,date: 25),weight: 34.5,
                                size: 66.9 ,fish: fishList.get(0),
                                fishingMan: fishingManList.get(0),
                                fishingArea: fishingAreaList.get(0))

        catchList << new Catch(date: new Date(year: 2010,month: 4,date: 7),weight: 66.5,
                                size: 99.9 ,fish: fishList.get(1),
                                fishingMan: fishingManList.get(1),
                                fishingArea: fishingAreaList.get(1))

        catchList << new Catch(date: new Date(year: 2011,month: 3,date: 9),weight: 788.5,
                                size: 156.3 ,fish: fishList.get(1),
                                fishingMan: fishingManList.get(1),
                                fishingArea: fishingAreaList.get(1))

        // Note
        noteList << new Note(fishingMan: fishingMan, fishingArea: fishingAreaList.get(0), value:3)
        noteList << new Note(fishingMan: fishingManList.get(1), fishingArea: fishingAreaList.get(0), value: 4)

        // Save them all !
        fishingManList.each {
            fishingManService.insertOrUpdateFishingMan(it)
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
        catchList.each {
            it.save(flush: true)
        }
        noteList.each {
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
        catchList.each {
            it.delete(flush: true)
        }
        noteList.each {
            it.delete(flush: true)
        }
    }
}
