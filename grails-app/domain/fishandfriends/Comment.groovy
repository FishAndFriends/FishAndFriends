package fishandfriends

import friendsfishand.ICommentable

/**
 * Class <b>Comment</b> which contains a date and a text.
 */
class Comment  {

    /** Date of the comment (default : current date */
    Date dateCreated

    /** Text of the comment */
    String text

    /** Fishing man which write the comment */
    FishingMan fishingMan


    ICommentable comment

    static constraints = {
    }
}
