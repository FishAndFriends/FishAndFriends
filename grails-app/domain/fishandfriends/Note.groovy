package fishandfriends

/**
 * Class to attribute a note to a <b>FisherMan</b> and a <b>FishingArea</b>.
 */
class Note {

    /** Fishing man link to the Note */
    FishingMan fishingMan

    /** Rated Fishing Area*/
    FishingArea fishingArea

    /** Value of the note */
    int value

    /**
     * Define constraints for a <b>Note</b> model.
     *  - <i>value</i> of the note is contains in 1 to 5
     *  - a note is given by an existing <i>fishingMan</i>
     *  - a note is attributed to an existing <i>fishingArea</i>
     */
    static constraints = {
        value(range: 1..5)
        fishingMan(nullable: false)
        fishingArea(nullable: false)
    }
}
