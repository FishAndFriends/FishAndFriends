package fishandfriends

import grails.transaction.Transactional
import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec

@Transactional
class FishingManService implements ISearchService {

    /**
     * insertOrUpdateFishingMan
     * @param the fishingMan to insert or update
     * @return the fishingMan just inserted
     */
    FishingMan insertOrUpdateFishingMan(FishingMan fishingMan) {
        if (fishingMan.tmpPassword != null) {
            // http://stackoverflow.com/a/8138604
            fishingMan.saltedPassword = generator((('A'..'Z') + ('0'..'9')).join(), 9)
            fishingMan.hashedPassword = SHA1Codec.encode(fishingMan.saltedPassword + fishingMan.tmpPassword)
        }

        fishingMan.save(flush: true)

        fishingMan.tmpPassword = null

        fishingMan
    }

    FishingMan findByEmail(String mail) {
        return FishingMan.findByEmail(mail)
    }

    @Override
    def search(int max, int offset, String query) {
        def criteria = FishingMan.createCriteria()

        List<FishingMan> result = criteria.list(max: max, offset: offset) {
            or {
                ilike 'lastname', "%${query}%"
                ilike 'firstname', "%${query}%"
            }
        }
        result
    }

    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }

    def controlPassword(FishingMan fishingMan, String password) {
        if (fishingMan) {
            def pwd = fishingMan.saltedPassword + password
            pwd = SHA1Codec.encode(pwd)

            if (fishingMan.hashedPassword.equals(pwd)) {
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }
}

