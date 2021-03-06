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
                        <i class="fa ${fishingManInstance.gender.equals("H") ? 'fa-male' : 'fa-female'} fa-5x"></i>
                    </div>

                    <div class="col-md-8">
                        <h2><b>${fishingManInstance.firstname}</b> ${fishingManInstance.lastname}
                        </h2>
                        Nombre de prises : ${score.nbCatch} <br/>
                        Poids moyen des prises : ${score.averageWeight} kg <br/>
                        Taille moyenne des prises : ${score.averageSize} cm <br/>
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
    </div>

    <div class="row">
        <div class="col-md-offset-2 col-md-2">

            <div class=" panel panel-warning catchPanel">
                <div class="panel-heading">
                    <b>Lieux</b>
                </div>

                <div class="panel-body">
                    <g:each var="fishingArea" in="${fishingAreas}">
                        <li>
                            <g:link action="show" controller="fishingArea" id="${fishingArea.id}">
                                ${fishingArea.name}, ${fishingArea.location}
                            </g:link>
                        </li>
                    </g:each>
                </div>
            </div>

        </div>

        <div class="col-md-6">
            <g:if test="${fishingManInstance.id.equals(session.fishingMan.id)}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4>Vous avez eu une prise ? <g:link action="share" controller="catch"
                                                                     class="btn btn-success">
                                    <i class="fa fa-share"></i> Partager
                                </g:link></h4>
                            </div>
                        </div>
                    </div>
                </div>
            </g:if>
            <g:each var="singleCatch" in="${catches}">
                <div class="row">
                    <div class="col-md-12">
                        <g:render template="showCatch"
                                  model="[singleCatch: singleCatch.aCatch, nbComments: singleCatch.nbComments]"></g:render>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>
