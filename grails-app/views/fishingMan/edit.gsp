<%@ page import="fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fishingMan.label', default: 'FishingMan')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="col-md-offset-2 col-md-4">
		<div class="homebox jumbotron">
			<h2>Édition du profil</h2>
			<g:form url="[action: 'editProfile', controller: 'fishingMan', resource: fishingManInstance]" class="form-horizontal">
				<div class="form-group">
					<label for="firstnameEdit" class="col-md-2 control-label">Prénom</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="firstnameEdit" name="firstnameEdit" value="${fishingManInstance.firstname}" placeholder="Prénom">
					</div>
				</div>
				<div class="form-group">
					<label for="lastnameEdit" class="col-md-2 control-label">Nom</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="lastnameEdit" name="lastnameEdit" value="${fishingManInstance.lastname}" placeholder="Nom">
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
		<g:form url="[action: 'editPassword', controller: 'fishingMan', resource: fishingManInstance]" class="form-horizontal">
			<div class="form-group">
				<label for="oldPassword" class="col-md-2 control-label">Ancien</label>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Ancien mot de passe">
				</div>
			</div>
			<div class="form-group">
				<label for="newPassword" class="col-md-2 control-label">Nouveau</label>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Nouveau mot de passe">
				</div>
			</div>
			<div class="col-md-offset-9">
				<button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i> Modifier</button>
			</div>
		</g:form>
	</div>
	</div>
	%{--</div>--}%
		%{--<a href="#edit-fishingMan" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
		%{--<div class="nav" role="navigation">--}%
			%{--<ul>--}%
				%{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
				%{--<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>--}%
				%{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
			%{--</ul>--}%
		%{--</div>--}%
		%{--<div id="edit-fishingMan" class="content scaffold-edit" role="main">--}%
			%{--<h1><g:message code="default.edit.label" args="[entityName]" /></h1>--}%
			%{--<g:if test="${flash.message}">--}%
			%{--<div class="message" role="status">${flash.message}</div>--}%
			%{--</g:if>--}%
			%{--<g:hasErrors bean="${fishingManInstance}">--}%
			%{--<ul class="errors" role="alert">--}%
				%{--<g:eachError bean="${fishingManInstance}" var="error">--}%
				%{--<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>--}%
				%{--</g:eachError>--}%
			%{--</ul>--}%
			%{--</g:hasErrors>--}%
			%{--<g:form url="[resource:fishingManInstance, action:'update']" method="PUT" >--}%
				%{--<g:hiddenField name="version" value="${fishingManInstance?.version}" />--}%
				%{--<fieldset class="form">--}%
					%{--<g:render template="form"/>--}%
				%{--</fieldset>--}%
				%{--<fieldset class="buttons">--}%
					%{--<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />--}%
				%{--</fieldset>--}%
			%{--</g:form>--}%
		%{--</div>--}%
	</body>
</html>
