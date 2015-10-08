
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
					<g class="row">
						<div class="col-md-2">
							<i class="fa ${fishingManInstance.gender.equals("H") ? 'fa-male':'fa-female'} fa-5x"></i>
						</div>
						<div class="col-md-8">
							<p>
								<h2><b>${fishingManInstance.firstname}</b> ${fishingManInstance.lastname}</h2>
								Poids moyen des prises : 0 kg <br/>
								Taille moyenne des prises : 0 cm <br/>
							</p>
						</div>
						<g:if test="${fishingManInstance.id}.equals(${session.fishingMan.id})">
							<div class="col-md-2">
								<a href="${createLink(action: "edit", controller: "fishingMan", resource: fishingManInstance)}"
								   class="btn btn-success" role="button"><i class="fa fa-pencil"></i> Modifier</a>
							</div>
						</g:if>
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
	</body>
</html>
