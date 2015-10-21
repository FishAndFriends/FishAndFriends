package fishandfriends

import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class CatchService {

    def sessionFactory

    def getCatchesByFishingMan(FishingMan afishingMan) {
        def criteria = Catch.createCriteria()

        def result = criteria.list() {
            fishingMan {
                idEq(afishingMan.id)

            }
        }
    }

    // TODO : remove
    def getCatchesByFishingArea(FishingArea afishingArea) {
        def criteria = Catch.createCriteria()

        def result = criteria.list() {
            fishingArea {
                idEq(afishingArea.id)
            }
        }

        return result
    }

    /**
     * Retrieves all catches with the number of comments on it, for a fishingArea.
     *
     * @param fishingAreaObj
     * @return
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
                                    GROUP BY ident)
                                ON catch.id = ident
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
     * Insert or update the Catch define by <i>aCatch</i>.
     * @param aCatch Catch to save into database.
     * @return Catch stored.
     */
    Catch insertOrUpdateCatch(Catch aCatch) {
        aCatch.save(flush: true)
    }
}
