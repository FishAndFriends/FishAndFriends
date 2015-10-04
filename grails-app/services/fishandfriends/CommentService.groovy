package fishandfriends

import grails.transaction.Transactional

/**
 * Created by Alexandre on 02/10/2015.
 */
@Transactional
class CommentService {

    def getAllCommentsForCommentable(AbstractCommentable abstractCommentable) {
        int max = 10
        int offset = 0

        def result = Comment.createCriteria().list() {//max: max, offset: offset) {
            commentable {
                idEq(abstractCommentable.id)
            }
        }

        return result
    }
}
