package fishandfriends

/**
 * Class for a Fishing area. A fishing area contains information on a area added
 * by a fishing man.
 */
class FishingArea extends AbstractCommentable {

    /** Name of the fishing area (e.g Garonne) */
    String name

    /** Location (e.g Toulouse)*/
    String location

    /** List of fishing man, fishes and catch for a FishingArea */
    static hasMany = [fishingMan : FishingMan, fish : Fish, catch : Catch]

    static constraints = {

    }
}
