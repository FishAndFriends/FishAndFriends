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
    <g:form url="[action: 'createComment', controller: 'comment']">
        <div class="panel panel-primary commentDisplay">
            <div class="panel-heading">Ajouter un commentaire</div>

            <div class="panel-body">
                <fieldset class="form">
                    <g:render template="form"/>
                </fieldset>

                <!-- FIXME : REMOVE IT ! -->
                <div class="debug" style="border: 1px solid; color:red">
                    <p>Options de debug</p>
                    <span>Objet à commenter :</span>
                    <g:select name="debug0" id="commentable" readonly="true" disabled="true"
                              from="${fishandfriends.AbstractCommentable.list()}"
                              optionKey="id"
                              required="" value="${commentable?.id}" class="many-to-one"/>
                    <br/>
                    <span>Pêcheur :</span>
                    <g:select name="debug1" id="fishingMan" readonly="true" disabled="true"
                              from="${fishandfriends.FishingMan.list()}" optionKey="id"
                              required=""
                              value="${fishingMan?.id}" class="many-to-one"/>
                </div>

                <input type="hidden" name="commentable.id" value="${commentable?.id}"/>
                <input type="hidden" name="fishingMan.id" value="${fishingMan?.id}"/>

                <br/>

                <fieldset class="buttons">
                    <input type="submit" class="btn btn-primary btn-block" value="Poster"/>
                </fieldset>
            </div>
        </div>
    </g:form>
</div>
