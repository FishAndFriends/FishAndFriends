package fishandfriends

/**
 * Class to attribute a note to a <b>FisherMan</b> and a <b>FishingArea</b>.
 */
class Note {

    /** Fishing man link to the Note */
    FishingMan fishingMan

    /* Rated Fishing Area*/
    FishingArea fishingArea

    /** Value of the note */
    int value

    static constraints = {
        value(range: 1..5)
        fishingMan(nullable: false)
        fishingArea(nullable: false)
    }
}
