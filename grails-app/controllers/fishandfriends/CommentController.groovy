package fishandfriends

class CommentController {

    def commentDAOService
    def commentService

    /**
     * Used to display the comment's page for a commentable object.
     * Call this method with params (model attribute) :
     *      - commentable : object with comments
     *      - fishingMan : the current logged user
     *
     * @return the view with the ability to post comments and to see all others comments
     */
    def index() {
        if (params.commentable == null) {
            // FIXME : Display error !!!
            System.out.println("[CommentController][index] COMMENTABLE NOT SET")
            params.commentable = FishingArea.findById(1)
        }
        if (params.fishingMan == null) {
            // FIXME : Display error !!!
            System.out.println("[CommentController][index] FISHMAN NOT SET")
            params.fishingMan = FishingMan.findById(1)
        }

        render(view: "index", model: [fishingMan: params.fishingMan, commentable: params.commentable])
    }

    /**
     * Method called by the template "_showAllComments".
     * To use that template, just put this line on the code :
     * <g:include controller="comment" action="showAllComment" params="[commentable:<ABSTRACT_COMMANTABLE>]" />
     *
     * @return the template rendered with all comments for the commentable object
     */
    def showAllComment() {
        if (params.commentable == null) {
            // FIXME : Display error !!!
            System.out.println("[CommentController][showAllComment] COMMENTABLE NOT SET")
            params.commentable = FishingArea.findById(1)
        }

        def comments = commentService.getAllCommentsForCommentable(params.commentable)

        render(template: "showAllComments", model: [comments: comments])
    }

    /**
     * This method is called when a user wants to create a comment.
     * If you want to display the form to submit comments, just put this line :
     * <g:render template="createComment" model="[commentable:<ABSTRACT_COMMENTABLE>,fishingMan:<FISHING_MAN>]"/>
     *
     * @return to the same page (normally)
     */
    def createComment() {
        Comment comment = new Comment(params)
        commentDAOService.saveComment(comment)

        redirect action: "index"
    }
}
