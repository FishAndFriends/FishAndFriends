<%@ page import="fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'fishingMan.label', default: 'FishingMan')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="row">
    <div class="col-md-offset-2 col-md-4">
        <div class="homebox jumbotron">
            <h2>Édition du profil</h2>
            <g:form url="[action: 'editProfile', controller: 'fishingMan', resource: fishingManInstance]"
                    class="form-horizontal">
                <div class="form-group">
                    <label for="firstnameEdit" class="col-md-2 control-label">Prénom</label>

                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="firstnameEdit" name="firstnameEdit"
                               value="${fishingManInstance.firstname}" placeholder="Prénom">
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastnameEdit" class="col-md-2 control-label">Nom</label>

                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="lastnameEdit" name="lastnameEdit"
                               value="${fishingManInstance.lastname}" placeholder="Nom">
                    </div>
                </div>

                <div class="col-md-offset-9">
                    <button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i> Modifier</button>
                </div>
            </g:form>
        </div>
    </div>

    <div class="col-md-4">
        <div class="homebox jumbotron">
            <h2>Changement de mot de passe</h2>
            <g:form url="[action: 'editPassword', controller: 'fishingMan', resource: fishingManInstance]"
                    class="form-horizontal">
                <div class="form-group">
                    <label for="oldPassword" class="col-md-2 control-label">Ancien</label>

                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                               placeholder="Ancien mot de passe">
                    </div>
                </div>

                <div class="form-group">
                    <label for="newPassword" class="col-md-2 control-label">Nouveau</label>

                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="newPassword" name="newPassword"
                               placeholder="Nouveau mot de passe">
                    </div>
                </div>

                <div class="col-md-offset-9">
                    <button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i> Modifier</button>
                </div>
            </g:form>
        </div>
    </div>
</div>
<g:hasErrors bean="${fishingManInstance}">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <div class="alert alert-danger" role="alert">
                <ul>
                    <g:eachError bean="${fishingManInstance}" var="error">
                        <li><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </div>
        </div>
    </div>
</g:hasErrors>
<g:if test="${errors}">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <div class="alert alert-danger" role="alert">
                <ul>
                    <g:each in="${errors}" var="error">
                        <li><g:message error="${error}"/></li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</g:if>
</body>
</html>
