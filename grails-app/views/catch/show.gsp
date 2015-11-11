
<%@ page import="fishandfriends.Catch" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catch.label', default: 'Catch')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-catch" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-catch" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list catch">
			
				<g:if test="${catchInstance?.date}">
				<li class="fieldcontain">
					<span id="date-label" class="property-label"><g:message code="catch.date.label" default="Date" /></span>
					
						<span class="property-value" aria-labelledby="date-label"><g:formatDate date="${catchInstance?.date}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${catchInstance?.weight}">
				<li class="fieldcontain">
					<span id="weight-label" class="property-label"><g:message code="catch.weight.label" default="Weight" /></span>
					
						<span class="property-value" aria-labelledby="weight-label"><g:fieldValue bean="${catchInstance}" field="weight"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${catchInstance?.size}">
				<li class="fieldcontain">
					<span id="size-label" class="property-label"><g:message code="catch.size.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${catchInstance}" field="size"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${catchInstance?.fishingMan}">
				<li class="fieldcontain">
					<span id="fishingMan-label" class="property-label"><g:message code="catch.fishingMan.label" default="Fishing Man" /></span>
					
						<span class="property-value" aria-labelledby="fishingMan-label"><g:link controller="fishingMan" action="show" id="${catchInstance?.fishingMan?.id}">${catchInstance?.fishingMan?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${catchInstance?.fishingArea}">
				<li class="fieldcontain">
					<span id="fishingArea-label" class="property-label"><g:message code="catch.fishingArea.label" default="Fishing Area" /></span>
					
						<span class="property-value" aria-labelledby="fishingArea-label"><g:link controller="fishingArea" action="show" id="${catchInstance?.fishingArea?.id}">${catchInstance?.fishingArea?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${catchInstance?.fish}">
				<li class="fieldcontain">
					<span id="fish-label" class="property-label"><g:message code="catch.fish.label" default="Fish" /></span>
					
						<span class="property-value" aria-labelledby="fish-label"><g:link controller="fish" action="show" id="${catchInstance?.fish?.id}">${catchInstance?.fish?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:catchInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${catchInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
