package fishandfriends

import javax.persistence.Transient

/**
 * Class of <b>FishingMan</b> which contains data of a fishing man.
 */
class FishingMan {

    /** Firstname of the fishing man */
    String firstname

    /** Lastname of the fishing man */
    String lastname
    
    /** Email of the fishing man */
    String email

    /** Hashed password of connection. */
    String hashedPassword

    /** Salted password of connection **/
    String saltedPassword

    /** Gender of the fishing man/woman. */
    String gender

    @Transient
    String tmpPassword

    /**
     * Define constraints for an <b>FishingMan</b>.
     *  - <i>firstname</i> need to be no empty
     *  - <i>lastname</i> need to be no empty
     *  - <i>email</i> need to be no empty, unique
     *  - <i>hashedPassword</i> need to be no empty
     *  - <i>saltedPassword</i> need to be no empty
     *  - <i>gender</i> need to be "H" or "F"
     *  - <i>tmpPassword</i> need to have a length of 8 or more characters
     */
    static constraints = {
        firstname nullable: false, blank: false
        lastname nullable: false, blank: false
        email email: true, nullable: false, blank: false, unique: true
        hashedPassword nullable: false, blank: false
        saltedPassword nullable: true, blank: true
        gender inList: ["H", "F"]
        tmpPassword nullable: true, validator: { val ->
            val == null || val.length() >= 8
        }
    }
}
