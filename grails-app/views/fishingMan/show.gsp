<%@ page import="fishandfriends.Catch; fishandfriends.FishingMan" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">

    <title>${fishingManInstance.firstname} ${fishingManInstance.lastname}</title>
</head>

<body>
<div class="row">
    <div class="col-md-offset-2 col-md-8">
        <div class="homebox jumbotron">
            <g class="row">
                <div class="col-md-2">
                    <i class="fa ${fishingManInstance.gender.equals("H") ? 'fa-male' : 'fa-female'} fa-5x"></i>
                </div>

                <div class="col-md-8">
                    <p>

                    <h2><b>${fishingManInstance.firstname}</b> ${fishingManInstance.lastname}
                    </h2>
                    Nombre de prises : ${score.nbCatch} <br/>
                    Poids moyen des prises : ${score.averageWeight} kg <br/>
                    Taille moyenne des prises : ${score.averageSize} cm <br/>
                </p>
                </div>
                <g:if test="${fishingManInstance.id.equals(session.fishingMan.id)}">
                    <div class="col-md-2">
                        <a href="${createLink(action: "edit", controller: "fishingMan", resource: fishingManInstance)}"
                           class="btn btn-success" role="button"><i
                                class="fa fa-pencil"></i> Modifier</a>
                    </div>
                </g:if>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-offset-2 col-md-2">

        <div class=" panel panel-warning catchPanel">
            <div class="panel-heading">
                <b>Lieux</b>
            </div>

            <div class="panel-body">
                <g:each var="fishingArea" in="${fishingAreas}">
                    <g:link action="show" controller="fishingArea" id="${fishingArea.id}">
                        <li>${fishingArea.name}, ${fishingArea.location}</li>
                    </g:link>
                </g:each>

            </div>
        </div>

    </div>

    <div class="col-md-6">
        <g:each var="singleCatch" in="${catches}">
            <div class="row">
                <div class="col-md-12">
                    <g:render template="showCatch"
                              model="[singleCatch: singleCatch]"></g:render>
                </div>
            </div>
        </g:each>
    </div>
</div>
</body>
</html>
