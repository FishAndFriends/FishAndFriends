package fishandfriends

/**
 * Catch for an only fish by a fishing man.
 */
class Catch extends AbstractCommentable {

    /** Date of the catch */
    Date date

    /** Weight of the fish catching (in "kg" unit) */
    Float weight

    /** Size of the fish catching (in "cm" unit) */
    Float size

    /** Fishing man which caught the fish */
    FishingMan fishingMan

    /** Fishing area where the fish has been catch */
    FishingArea fishingArea

    /** Fish which has been catch by the fishing man in the Fishing area */
    Fish fish

    static constraints = {
        date nullable: false, validator: {
            val, obj -> val?.after(new Date())
        }
        weight min: 0.1F, nullable: false
        size min: 0.1F, nullable: false
        fishingMan nullable: false
        fishingArea nullable: false
        fish nullable: false
    }
}
