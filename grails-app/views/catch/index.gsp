
<%@ page import="fishandfriends.Catch" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catch.label', default: 'Catch')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-catch" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-catch" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="date" title="${message(code: 'catch.date.label', default: 'Date')}" />
					
						<g:sortableColumn property="weight" title="${message(code: 'catch.weight.label', default: 'Weight')}" />
					
						<g:sortableColumn property="size" title="${message(code: 'catch.size.label', default: 'Size')}" />
					
						<th><g:message code="catch.fishingMan.label" default="Fishing Man" /></th>
					
						<th><g:message code="catch.fishingArea.label" default="Fishing Area" /></th>
					
						<th><g:message code="catch.fish.label" default="Fish" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${catchInstanceList}" status="i" var="catchInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${catchInstance.id}">${fieldValue(bean: catchInstance, field: "date")}</g:link></td>
					
						<td>${fieldValue(bean: catchInstance, field: "weight")}</td>
					
						<td>${fieldValue(bean: catchInstance, field: "size")}</td>
					
						<td>${fieldValue(bean: catchInstance, field: "fishingMan")}</td>
					
						<td>${fieldValue(bean: catchInstance, field: "fishingArea")}</td>
					
						<td>${fieldValue(bean: catchInstance, field: "fish")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${catchInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
