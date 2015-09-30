package fishandfriends

/**
 * Catch for an only fish by a fishing man.
 */
class Catch {

    /** Date of the catch */
    Date date

    /** Weight of the fish catching */
    Float weight

    /** Size of the fish catching */
    Float size

    /** Fishing man which caught the fish */
    FishingMan fishingMan

    /** Fishing area where the fish has been catch */
    FishingArea fishingArea

    /** Fish which has been catch by the fishing man in the Fishing area */
    Fish fish

    static constraints = {
    }
}
