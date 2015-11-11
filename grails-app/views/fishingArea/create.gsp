<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="layout" content="main"/>
</head>

<body>
<div class="container-fluid">
            <div class="col-md-offset-3 col-md-6">
			<div class="homebox jumbotron">
				<h2>Ajout d'une nouvelle zone de pêche</h2>
				<g:form url="[action: 'createFishingArea', controller: 'fishingArea']">
					<div class="form-group">
							<input type="text" class="form-control" name="fishingAreaName" value="${params.fishingAreaName}" placeholder="Nom" required>
						</div>
					<div class="form-group">
							<input type="text" class="form-control" name="fishingAreaLocation" value="${params.fishingAreaLocation}" placeholder="Lieux" required>
						</div>
					<div class="form-group">
                        <textarea style="resize:vertical" class="span6 form-control" rows="10" name="fishingAreaDescription" placeholder="Description" value="${params.fishingAreaDescription}" required="required"></textarea>

                    </div>


					<button type="submit" class="btn btn-success"><i class="fa fa-plus"></i> Ajouter </button>
				</g:form>
			</div>
		</div>
	</div>


<%---
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
	</g:if>  --%>
</div>
</body>
</html>