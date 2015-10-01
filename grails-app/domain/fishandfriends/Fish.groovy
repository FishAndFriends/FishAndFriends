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

    static constraints = {
        name(nullable: false, blank: false)
        weightAverage(min: 0)
        sizeAverage(min: 0)
    }
}
