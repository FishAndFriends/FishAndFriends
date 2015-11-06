package fishandfriends

import grails.transaction.Transactional

@Transactional
class NoteService {

    /**
     * Saves or updates a note object.
     * @param aFishingMan the fishingMan who gives the note to ...
     * @param aFishingArea the object noted.
     * @param note the note given.
     * @return the note object saved.
     */
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

    /**
     * Retrieves the note given by a fishingMan on a fishingArea.
     * @param aFishingMan the fishingMan who gave the note.
     * @param aFishingArea the fishingArea noted.
     * @return the note given.
     */
    def getNoteGivenByAFishingMan(FishingMan aFishingMan, FishingArea aFishingArea) {
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

        if (!res) {
            return 0
        } else {
            return res
        }
    }
}
