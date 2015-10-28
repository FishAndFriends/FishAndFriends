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

    /**
     * Define constraints for an <b>Comment</b>.
     *  - <i>text</i> need to be not empty
     *  - Comment need to link to a <i>fishingMan</i>
     */
    static constraints = {
        text nullable: false, blank: false, size: 1..5000
        commentable nullable: false
        fishingMan nullable: false
    }

    /** Define if the model <b>Comment</b> need to be linked to database. */
    static mapping = {
        autoTimestamp true
    }
}
