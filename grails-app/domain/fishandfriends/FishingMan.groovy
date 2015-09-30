package fishandfriends

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

    /** Password of connection. */
    String password

    /** Gender of the fishing man/woman. */
    String gender

    static constraints = {
        firstname nullable: false , blank: false
        lastname nullable: false , blank: false
        email email: true , nullable: false ,blank: false
        password minSize: 5 , nullable: false , blank: false
        gender inList: ["H","F"]
    }
}
