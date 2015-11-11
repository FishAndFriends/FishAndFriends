package fishandfriends

import grails.transaction.Transactional
import groovy.sql.Sql

/**
 * Service of a Catch uses to get Catch information according data :
 *     - Catches of a given Fishing Man
 *     - Catches with these comments for a given fishing area.
 */
@Transactional
class CatchService {

    FishingAreaService fishingAreaService
    /**
     * Session Factory to access to database.
     */
    def sessionFactory

    /**
     * Return the list of Catches with the number of comments for a given Fishing Man <i>afishingMan</i>.
     *
     * @param afishingMan Fishing Man where we want to get Catches.
     * @return the list of catches with the number of comment on them.
     */
    def getCatchesWithNbCommentsByFishingMan(FishingMan afishingMan) {
        def listCatchWithComments = []
        def sql = new Sql(sessionFactory.currentSession.connection())
        def result = sql.rows("""
                        SELECT
                            FISHING_MAN_ID as fishingMan_id,
                            FIRSTNAME as fishingMan_firstname,
                            LASTNAME as fishingMan_lastname,
                            COMMENTS as comments,
                            WEIGHT as weight,
                            SIZE as size,
                            FISHING_AREA_ID as fishingArea_id,
                            LOCATION as fishingArea_location,
                            fishingArea.NAME as fishingArea_name,
                            DATE as date,
                            TMP.ID as id,
                            fish.NAME as fish_name
                        FROM
                            Fishing_Man as fishingman,
                            Fish as fish,
                            Fishing_Area as fishingArea,
                            (
                                SELECT
                                    *
                                FROM
                                    Catch as catch
                                LEFT JOIN (
                                    SELECT
                                        count(*) as comments,
                                        comment.COMMENTABLE_ID as ident
                                    FROM Comment as comment
                                    GROUP BY ident) as t
                                ON catch.id = t.ident
                                WHERE
                                    catch.FISHING_MAN_ID = (${afishingMan.id})
                            ) as TMP
                        WHERE
                            TMP.FISHING_MAN_ID = fishingman.id
                            AND TMP.FISH_ID = fish.ID
                            AND TMP.FISHING_AREA_ID = fishingArea.ID
                        ORDER BY
                            date DESC
                        """)

        // Map result query with fantom objects
        Fish fishF
        FishingMan fishingManF
        FishingArea fishingAreaF
        Catch aCatchF

        result?.each {
            fishF = new Fish(name: it.fish_name)

            fishingAreaF = new FishingArea(location: it.fishingArea_location, name: it.fishingArea_name)
            fishingAreaF.id = it.fishingArea_id

            fishingManF = new FishingMan(firstname: it.fishingMan_firstname, lastname: it.fishingMan_lastname)
            fishingManF.id = it.fishingMan_id

            aCatchF = new Catch(weight: it.weight, size: it.size, date: it.date, fish: fishF, fishingMan: afishingMan, fishingArea: fishingAreaF)
            aCatchF.id = it.id

            listCatchWithComments << new CatchWithComments(aCatchF, it.comments ? it.comments : 0l)
        }

        return listCatchWithComments
    }

    /**
     * Retrieves all catches with the number of comments on it, for a fishingArea.
     *
     * @param fishingAreaObj the fishingArea where the catches are.
     * @return the list of catches with the number of comment on them.
     */
    def getCatchesWithNbCommentsByFishingArea(FishingArea fishingAreaObj) {
        def listCatchWithComments = []
        def sql = new Sql(sessionFactory.currentSession.connection())
        def result = sql.rows("""
                        SELECT
                            FISHING_MAN_ID as fishingMan_id,
                            FIRSTNAME as fishingMan_firstname,
                            LASTNAME as fishingMan_lastname,
                            COMMENTS as comments,
                            WEIGHT as weight,
                            SIZE as size,
                            FISHING_AREA_ID as fishingArea_id,
                            DATE as date,
                            TMP.ID as id,
                            NAME as fish_name
                        FROM
                            Fishing_Man as fishingman,
                            Fish as fish,
                            (
                                SELECT
                                    *
                                FROM
                                    Catch as catch
                                LEFT JOIN (
                                    SELECT
                                        count(*) as comments,
                                        comment.COMMENTABLE_ID as ident
                                    FROM Comment as comment
                                    GROUP BY ident) as t
                                ON catch.id = t.ident
                                WHERE
                                    catch.FISHING_AREA_ID = ${fishingAreaObj.id}
                            ) as TMP
                        WHERE
                            TMP.FISHING_MAN_ID = fishingman.id
                            AND TMP.FISH_ID = fish.ID
                        """)

        // Map result query with fantom objects
        Fish fish
        FishingMan fishingMan
        FishingArea fishingArea
        Catch aCatch

        result?.each {
            fish = new Fish(name: it.fish_name)

            fishingArea = new FishingArea()
            fishingArea.id = it.fishingArea_id

            fishingMan = new FishingMan(firstname: it.fishingMan_firstname, lastname: it.fishingMan_lastname)
            fishingMan.id = it.fishingMan_id

            aCatch = new Catch(weight: it.weight, size: it.size, date: it.date, fish: fish, fishingMan: fishingMan, fishingArea: fishingArea)
            aCatch.id = it.id

            listCatchWithComments << new CatchWithComments(aCatch, it.comments ? it.comments : 0l)
        }

        return listCatchWithComments
    }


    /**
     * Retrieves all catches with the number of comments on it, for all fishingAreas subscribed by the given fishingMan.
     *
     * @param fishingMan the fishingMan concerned by the catches
     * @return the list of catches with the number of comment on them.
     */
    def getCatchesWithNbCommentsForNewsfeed(FishingMan fishingMan) {
        def listCatchWithComments = []
        def fishingAreas = fishingAreaService.getFishingAreaByFishingMan(fishingMan)

        if (fishingAreas.isEmpty()) {
            return listCatchWithComments
        }

        def sql = new Sql(sessionFactory.currentSession.connection())
        def result = sql.rows("""
                        SELECT
                            FISHING_MAN_ID as fishingMan_id,
                            FIRSTNAME as fishingMan_firstname,
                            LASTNAME as fishingMan_lastname,
                            COMMENTS as comments,
                            WEIGHT as weight,
                            SIZE as size,
                            FISHING_AREA_ID as fishingArea_id,
                            LOCATION as fishingArea_location,
                            fishingArea.NAME as fishingArea_name,
                            DATE as date,
                            TMP.ID as id,
                            fish.NAME as fish_name
                        FROM
                            Fishing_Man as fishingman,
                            Fish as fish,
                            Fishing_Area as fishingArea,
                            (
                                SELECT
                                    *
                                FROM
                                    Catch as catch
                                LEFT JOIN (
                                    SELECT
                                        count(*) as comments,
                                        comment.COMMENTABLE_ID as ident
                                    FROM Comment as comment
                                    GROUP BY ident) as t
                                ON catch.id = t.ident
                                WHERE
                                    catch.FISHING_AREA_ID IN (${fishingAreas*.id.join(',')})
                            ) as TMP
                        WHERE
                            TMP.FISHING_MAN_ID = fishingman.id
                            AND TMP.FISH_ID = fish.ID
                            AND TMP.FISHING_AREA_ID = fishingArea.ID
                        ORDER BY
                            date DESC
                        """)

        // Map result query with fantom objects
        Fish fishF
        FishingMan fishingManF
        FishingArea fishingAreaF
        Catch aCatchF

        result?.each {
            fishF = new Fish(name: it.fish_name)

            fishingAreaF = new FishingArea(location: it.fishingArea_location, name: it.fishingArea_name)
            fishingAreaF.id = it.fishingArea_id

            fishingManF = new FishingMan(firstname: it.fishingMan_firstname, lastname: it.fishingMan_lastname)
            fishingManF.id = it.fishingMan_id

            aCatchF = new Catch(weight: it.weight, size: it.size, date: it.date, fish: fishF, fishingMan: fishingMan, fishingArea: fishingAreaF)
            aCatchF.id = it.id

            listCatchWithComments << new CatchWithComments(aCatchF, it.comments ? it.comments : 0l)
        }

        return listCatchWithComments
    }
}
