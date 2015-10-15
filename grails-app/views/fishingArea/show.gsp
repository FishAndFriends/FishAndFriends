
<%@ page import="fishandfriends.Catch; fishandfriends.FishingArea" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">

	<title>${fishingAreaInstance.name}, ${fishingAreaInstance.location}</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<div class="homebox jumbotron">
				<g class="row">
					<div class="col-md-8">
						<p>
							<h2><i class="fa fa-map-marker"></i> <b>${fishingAreaInstance.name},</b> ${fishingAreaInstance.location}</h2>
							Note : <g:if test="${score.note >= 0}">${score.note}/5</g:if><g:else>Aucune note</g:else><br/>
							Total de prises : <g:if test="${score.nbCatch > 0}">${score.nbCatch}</g:if><g:else>Aucune prise</g:else>  <br/>
							Nombre de personnes : <g:if test="${score.nbFishingMan > 0}">${score.nbFishingMan}</g:if><g:else>Aucun pêcheur abonné</g:else> <br/>
						</p>
					</div>

					<div class="col-md-2">
						<button type="button" class="btn btn-primary">Suivre</button>
					</div>
					</g>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-2 col-md-3">
		<div class="homebox jumbotron">
			<h2>Description</h2>

					${fishingAreaInstance.description}
			<br/>
			<h2>Note</h2>
			<select id="note" name="note" class="">
				<option value="note1">1</option>
				<option value="note2">2</option>
				<option value="note3">3</option>
				<option value="note4">4</option>
				<option value="note5">5</option>
			</select>
			<button type="button" class="btn btn-primary">OK</button>

			<h2>Commentaires</h2>

			Nombre de commentaire : <br/>
			<a>Voir</a>


		</div>
	</div>
	<div class="col-md-6">
		<g:set var="counter" value="${1}" />
		<g:each var="singleCatch" in="${catches}">
			<div class="row">
				<div class="col-md-9">
					<g:render template="showCatch" model="[singleCatch: singleCatch,counter: counter]"></g:render>
				</div>
			</div>
			<g:set var="counter" value="${counter + 1}" />
		</g:each>
	</div>
	</div>
</div>
</body>
</html>
