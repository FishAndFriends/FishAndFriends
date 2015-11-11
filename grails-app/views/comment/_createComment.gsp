<!--
    Template used to display the form to create a comment.

    Should take, as parameters :
        - fishingMan : the fishMan who wants to post a comment
        - commentable : the object to comment
-->

<style>
.commentDisplay {
    width: 100%;
}

.commentDisplay table {
    width: 100%;
}
</style>

<div id="create-comment" class="content scaffold-create" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${commentInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${commentInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form class="formComment" url="[action: 'createComment', controller: 'comment']">
        <div class="panel panel-info commentDisplay catchPanel">
            <div class="panel-heading"><b>Ajouter un commentaire</b></div>

            <div class="panel-body">
                <fieldset class="form">
                    <g:render template="../comment/form"/>
                </fieldset>

                <input type="hidden" name="commentable.id" value="${commentable?.id}"/>
                <input type="hidden" name="fishingMan.id" value="${fishingMan?.id}"/>

                <br/>

                <fieldset class="buttons">
                    <button type="submit" class="btn btn-primary btn-block">Poster</button>
                </fieldset>
            </div>
        </div>
    </g:form>
</div>
