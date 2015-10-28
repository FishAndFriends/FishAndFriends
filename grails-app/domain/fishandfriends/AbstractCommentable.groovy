package fishandfriends

/**
 * Abstract class to define a <b>AbstractCommentable</b>.
 */
abstract class AbstractCommentable {

    /**
     * Define constraints for an <b>AbstractCommentable</b>.
     */
    static constraints = {
    }

    /**
     * Define if the model <b>AbstractCommentable</b> need to be linked to
     * database.
     */
    static mapping = {
        tablePerHierarchy false
    }
}
