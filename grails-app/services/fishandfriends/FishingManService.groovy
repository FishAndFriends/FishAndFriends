package fishandfriends

import grails.transaction.Transactional
import org.codehaus.groovy.grails.plugins.codecs.SHA1Codec

/**
 * Service for a FishingMan to insert, modify or get data from database.
 */
@Transactional
class FishingManService implements ISearchService {

    /**
     * Insert in database a Fishing Man <i>fishingMan</i>.
     *
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

    /**
     * Return the fishing Man which match to the email address <i>mail</i>.
     *
     * @param mail Email address.
     * @return Fishing Man which match to the email address
     */
    FishingMan findByEmail(String mail) {
        return FishingMan.findByEmail(mail)
    }

    /**
     * Return a list of Fishing Area existing in database according a
     * <i>query</i>. The <i>query</i> could be a last name or first name of a
     * Fishing Man.
     * <i>max</i> argument is used to define the number of maximum results to
     * display.
     * <i>offset</i> specifies number of elements per page.
     *
     * @param max Maximum number of results
     * @param offset Maximum number of results per page.
     * @param query Name or location Area.
     * @return List of Fishing Areas which match with the <i>query</i>.
     */
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

    /**
     *
     */
    def generator = { String alphabet, int n ->
        new Random().with {
            (1..n).collect { alphabet[nextInt(alphabet.length())] }.join()
        }
    }

    /**
     * Return TRUE if the given <i>password</i> of the Fishing Man
     * <i>fishingMan</i> matches ; otherwise return FALSE.
     * @param fishingMan Fishing man who want to sign in.
     * @param password Password entered y the Fishing Man.
     * @return Boolean control value.
     */
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

