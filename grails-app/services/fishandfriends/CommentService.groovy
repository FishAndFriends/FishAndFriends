package fishandfriends

import grails.transaction.Transactional

@Transactional
class CommentService {

    def saveComment(Comment comment){
        comment.fishingMan.save true
        comment.commentable.save true

        comment.save true
    }
}
