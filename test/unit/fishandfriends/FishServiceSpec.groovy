package fishandfriends

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(FishService)
@Mock([Fish])
class FishServiceSpec extends Specification {

    Fish fish,fish2,fish3,fish4,fish5

    def setup() {
        fish  = new Fish(name: "Carpe",weightAverage: 2.4,sizeAverage: 30).save(flush: true)
        fish2 = new Fish(name: "Carpe d'eau",weightAverage: 3,sizeAverage: 26).save(flush: true)
        fish3 = new Fish(name: "Carpe diem",weightAverage:1.8,sizeAverage: 24).save(flush: true)
        fish4 = new Fish(name: "Silure",weightAverage: 4.2,sizeAverage:20).save(flush: true)
        fish5 = new Fish(name: "poisson clown",weightAverage: 0.50,sizeAverage: 22).save(flush: true)
    }

    def cleanup() {
        fish.delete(flush: true)
        fish2.delete(flush: true)
        fish3.delete(flush: true)
        fish4.delete(flush: true)
        fish5.delete(flush: true)
    }

    void "test searching 3 fishes"() {
        when:"searching for fishes"
        def result = service.search(5,0,"carpe")

        then:"the result contains 3 fishes"
        result.size() == 3
        result.contains(fish)
        result.contains(fish2)
        result.contains(fish3)
        !result.contains(fish4)
        !result.contains(fish5)
    }

    void "test searching one fish in particular"() {
        when:"searching for one fish"
        def result = service.search(5,0,"silure")

        then:"the result contains 1 fish"
        result.size() == 1
        !result.contains(fish)
        !result.contains(fish2)
        !result.contains(fish3)
        result.contains(fish4)
        !result.contains(fish5)
    }

    void "test search return 0 result"() {
        when:"searching for a fish not in the database"
        def result = service.search(5,0,"brochet")

        then:"the result contains nothing"
        result.size() == 0
        !result.contains(fish)
        !result.contains(fish2)
        !result.contains(fish3)
        !result.contains(fish4)
        !result.contains(fish5)
    }
}
