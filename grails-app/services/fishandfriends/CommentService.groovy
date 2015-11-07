package fishandfriends

import grails.transaction.Transactional

/**
 * Created by Alexandre on 02/10/2015.
 */
@Transactional
class CommentService {

    /**
     * Retrieve all comments on a commentable object.
     * @param obj to retrieve comments on.
     * @return all comments on the object.
     */
    def getAllCommentsForCommentable(def obj) {

        long id = extractId(obj)

        def result = Comment.createCriteria().list() {
            commentable {
                idEq(id)
            }
        }

        return result
    }

    /**
     * Get the 5 lastest comments on a commentable object.
     * @param obj to retrieve comments on.
     * @return the 5 lastest comments on the object.
     */
    def get5CommentsForCommentable(def obj) {

        long id = extractId(obj)

        def result = Comment.createCriteria().list(max: 5) {
            commentable {
                idEq(id)
            }

            order('dateCreated', 'desc')
        }

        return result
    }

    /**
     * Extract id from an object.
     * This allow us more flexibility with templating.
     *
     * @param obj
     * @return -1 if not found, else, the id as long object.
     */
    private long extractId(def obj) {
        long id

        if (obj instanceof String) {
            try {
                id = Long.parseLong(obj)
            } catch (Exception) {
                id = -1l
            }
        } else if (obj instanceof Long) {
            id = obj
        } else if (obj instanceof AbstractCommentable) {
            if (obj.id) {
                id = obj.id
            } else {
                id = -1l;
            }
        } else if (obj instanceof Integer) {
            id = obj
        } else {
            id = -1l
        }

        return id
    }
}
