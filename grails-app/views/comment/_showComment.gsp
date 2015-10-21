<%@ page import="fishandfriends.Comment" %>
<!--
    Template used to display a comment.
    It prevent Javascript injection but allow HTML injection

    Should take, as parameters :
        - comment : object comment to display
-->

<style>
.commentDisplay {
    width: 100%;
}

.commentDisplay table {
    width: 100%;
}
</style>

<div class="panel panel-info commentDisplay catchPanel">
    <div class="panel-heading">
        <table class="">
            <tbody>
            <tr>
                <td>
                    <b><span>${comment?.fishingMan?.firstname} ${comment?.fishingMan?.lastname}</span> <span>-</span> <span><g:formatDate format="yyyy-MM-dd HH:mm" date="${comment?.dateCreated}"/></span></b>
                </td>
                <td>
                    <!--button disabled style="float:right" class="btn btn-default">Supprimer</button-->
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="panel-body">
        ${raw(comment?.text.replaceAll("\\n", "<br/>").replaceAll("<script>", "&lt;script&gt;").replaceAll("</script>", "&lt;/script&gt;").replaceAll("<script", "&lt;script"))}
    </div>
</div>
