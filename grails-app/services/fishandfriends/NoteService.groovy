package fishandfriends

import grails.transaction.Transactional

@Transactional
class NoteService {

    def insertOrUpdate(FishingMan aFishingMan, FishingArea aFishingArea, int note) {

        def result = Note.createCriteria().get {
            fishingArea {
                idEq(aFishingArea.id)
            }

            fishingMan {
                idEq(aFishingMan.id)
            }
        }

        if (result) {
            result.value = note
        } else {
            result = new Note(fishingArea: aFishingArea, fishingMan: aFishingMan, value: note)
        }

        result.save(flush: true)
    }

    def getNoteGivenByAFishingMan(FishingMan aFishingMan, FishingArea aFishingArea){
        def res = Note.createCriteria().get {
            fishingArea {
                idEq(aFishingArea.id)
            }

            fishingMan {
                idEq(aFishingMan.id)
            }

            projections {
                property 'value'
            }

            cache true
        }

        if(!res){
            return 0
        }
        else{
            return res
        }
    }
}