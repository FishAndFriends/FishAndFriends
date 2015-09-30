package friendsfishand;

import fishandfriends.Comment;

/**
 * Created by florent on 30/09/15.
 */
public interface ICommentable {

    /**
     * Add a text <i>txt</i> comment and return the <b>Comment</b> linked.
     * @param txt Text of the comment.
     * @return Comment
     */
    Comment addComment(final String txt);

}
