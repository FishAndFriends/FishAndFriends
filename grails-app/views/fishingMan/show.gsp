
<%@ page import="fishandfriends.Catch; fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">

		<title>${fishingManInstance.firstname} ${fishingManInstance.lastname}</title>
	</head>
	<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<div class="homebox jumbotron">
					<div class="row">
						<div class="col-md-2">
							<i class="fa ${fishingManInstance.gender.equals("H") ? 'fa-male':'fa-female'} fa-5x"></i>
						</div>
						<div class="col-md-10">
							<p>
								<h2><b>${fishingManInstance.firstname}</b> ${fishingManInstance.lastname}</h2>
								Poids moyen des prises : 0 kg <br/>
								Taille moyenne des prises : 0 cm <br/>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-2 col-md-2">
				<div class="homebox jumbotron">
					<h2>Lieux</h2>
					<ul>
						<g:each var="fishingArea" in="${fishingAreas}">
							<li>${fishingArea.name}, ${fishingArea.location}</li>
						</g:each>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<g:each var="singleCatch" in="${catches}">
					<div class="row">
						<div class="col-md-12">
							<g:render template="showCatch" model="[singleCatch: singleCatch]"></g:render>
						</div>
					</div>
				</g:each>
			</div>
		</div>
	</div>
		<div id="show-fishingMan" class="content scaffold-show" role="main">


			<h1></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fishingMan">
			
				<g:if test="${fishingManInstance?.firstname}">
				<li class="fieldcontain">
					<span id="firstname-label" class="property-label"><g:message code="fishingMan.firstname.label" default="Firstname" /></span>
					
						<span class="property-value" aria-labelledby="firstname-label"><g:fieldValue bean="${fishingManInstance}" field="firstname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fishingManInstance?.lastname}">
				<li class="fieldcontain">
					<span id="lastname-label" class="property-label"><g:message code="fishingMan.lastname.label" default="Lastname" /></span>
					
						<span class="property-value" aria-labelledby="lastname-label"><g:fieldValue bean="${fishingManInstance}" field="lastname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fishingManInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="fishingMan.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${fishingManInstance}" field="email"/></span>
					
				</li>
				</g:if>

				<g:if test="${fishingManInstance?.gender}">
				<li class="fieldcontain">
					<span id="gender-label" class="property-label"><g:message code="fishingMan.gender.label" default="Gender" /></span>
					
						<span class="property-value" aria-labelledby="gender-label"><g:fieldValue bean="${fishingManInstance}" field="gender"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:fishingManInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${fishingManInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
