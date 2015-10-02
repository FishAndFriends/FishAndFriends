package fishandfriends

import grails.transaction.Transactional

@Transactional
class CommentDAOService {

    def saveComment(Comment comment){
        comment.fishingMan = comment.fishingMan.save(flush: true)
        comment.commentable = comment.commentable.save(flush: true)

        comment.save(flush: true)
    }
}