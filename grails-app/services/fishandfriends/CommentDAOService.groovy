package fishandfriends

import grails.transaction.Transactional

@Transactional
class CommentDAOService {

    /**
     * Save a comment object.
     * @param comment to save.
     * @return the object saved.
     */
    def saveComment(Comment comment){
        comment.fishingMan = comment.fishingMan.save(flush: true)
        comment.commentable = comment.commentable.save(flush: true)

        comment.save(flush: true, failOnError: true)
    }
}