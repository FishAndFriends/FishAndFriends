package fishandfriends

class CommentController {

    def commentDAOService
    def commentService

    /**
     * Method called by the template "_showListComments".
     * To use that template, just put this line on the code :
     * <g:include controller="comment" action="showAllComment" params="[commentable:<ABSTRACT_COMMANTABLE>]" />
     *
     * @return the template rendered with all comments for the commentable object
     */
    def showAllComment() {
        def comments = commentService.getAllCommentsForCommentable(params.commentable)
        render(template: "showCommentsList", model: [comments: comments])
    }

    /**
     * Method called by the template "_showListComments".
     * To use that template, just put this line on the code :
     * <g:include controller="comment" action="show5Comments" params="[commentable:<ABSTRACT_COMMANTABLE>]" />
     *
     * @return the template rendered with the 5 last comments for the commentable object
     */
    def show5Comments() {
        def comments = commentService.get5CommentsForCommentable(params.commentable)
        render(template: "showCommentsList", model: [comments: comments])
    }

    /**
     * This method is called when a user wants to create a comment.
     * If you want to display the form to submit comments, just put this line :
     * <g:render template="createComment" model="[commentable:<ABSTRACT_COMMENTABLE>,fishingMan:<FISHING_MAN>,
     *      formid:'<UNIQUE_HTML_ID>']"/>
     *
     * @return to the same page (normally)
     */
    @SuppressWarnings('GrailsMassAssignment') // We don't want to modify that code when the model is updated
    def createComment() {
        Comment comment = new Comment(params)
        commentDAOService.saveComment(comment)

        render ''
    }
}
