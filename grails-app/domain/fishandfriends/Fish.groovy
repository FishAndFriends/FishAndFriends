package fishandfriends

/**
 * Class with data according the type of fish.
 */
class Fish {

    /** Fish name */
    String name

    /** Weight for this type of fish */
    float weightAverage

    /** Size for this type of fish */
    float sizeAverage

    /**
     * Define constraints for an <b>Comment</b>.
     *  - <i>name</i> need to be no empty
     *  - <i>weight</i> need to have a positive value
     *  - <i>size</i> need to have a positive value
     */
    static constraints = {
        name(nullable: false, blank: false)
        weightAverage(min: 0f)
        sizeAverage(min: 0f)
    }
}
