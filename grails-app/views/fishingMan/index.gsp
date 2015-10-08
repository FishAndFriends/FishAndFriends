
<%@ page import="fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fishingMan.label', default: 'FishingMan')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fishingMan" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fishingMan" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="firstname" title="${message(code: 'fishingMan.firstname.label', default: 'Firstname')}" />
					
						<g:sortableColumn property="lastname" title="${message(code: 'fishingMan.lastname.label', default: 'Lastname')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'fishingMan.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="password" title="${message(code: 'fishingMan.hashedPassword.label', default: 'Password')}" />
					
						<g:sortableColumn property="gender" title="${message(code: 'fishingMan.gender.label', default: 'Gender')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${fishingManInstanceList}" status="i" var="fishingManInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fishingManInstance.id}">${fieldValue(bean: fishingManInstance, field: "firstname")}</g:link></td>
					
						<td>${fieldValue(bean: fishingManInstance, field: "lastname")}</td>
					
						<td>${fieldValue(bean: fishingManInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: fishingManInstance, field: "hashedPassword")}</td>
					
						<td>${fieldValue(bean: fishingManInstance, field: "gender")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fishingManInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
