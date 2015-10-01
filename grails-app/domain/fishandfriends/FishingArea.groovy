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
    static hasMany = [fishingMen: FishingMan, fishes: Fish, catches : Catch]

    static constraints = {

        name nullable: false, blank: false
        location nullable: false, blank: false

    }
}
