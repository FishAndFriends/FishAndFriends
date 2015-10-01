package fishandfriends

/**
 * Class <b>Comment</b> which contains a date and a text.
 */
class Comment {

    /** Date of the comment (default : current date */
    Date dateCreated

    /** Text of the comment */
    String text

    /** Fishing man which write the comment */
    FishingMan fishingMan

    /** Object to comment */
    AbstractCommentable commentable

    static constraints = {
        text nullable: false, blank: false, size: 1..5000
        commentable nullable: false
        fishingMan nullable: false
    }

    static mapping = {
        autoTimestamp true
    }
}
