package fishandfriends

import grails.transaction.Transactional

/**
 * Created by Alexandre on 02/10/2015.
 */
@Transactional
class CommentService {

    def getAllCommentsForCommentable(AbstractCommentable abstractCommentable) {

        def result = Comment.createCriteria().list() {
            commentable {
                idEq(abstractCommentable.id)
            }
        }

        return result
    }
}
