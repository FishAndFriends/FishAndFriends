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

    /** Description du lieu) */
    String description

    /** List of fishing man, fishes and catch for a FishingArea */
    static hasMany = [fishingMen: FishingMan, fishes: Fish]

    /**
     * Define constraints for an <b>FishingArea</b>.
     *  - A Fishing area need to have a no empty name
     *  - A Fishing area need to have a no empty location
     *  - A Fishing area need to have a no empty description
     */
    static constraints = {
        name nullable: false, blank: false
        location nullable: false, blank: false
        description nullable: false, blank: false
    }

    static mapping = {
        description type: 'text'
    }
}
