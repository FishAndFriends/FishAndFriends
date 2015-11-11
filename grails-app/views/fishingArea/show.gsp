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
                <div class="row">
                    <div class="col-md-8">
                        <h2><i class="fa fa-map-marker"></i> <b>${fishingAreaInstance.name},</b> ${fishingAreaInstance.location}
                        </h2>

                        <p>
                            Note : <g:if
                                test="${score.note >= 0}">${score.note}/5</g:if><g:else>Aucune note</g:else><br/>
                            Total de prises : <g:if
                                    test="${score.nbCatch > 0}">${score.nbCatch}</g:if><g:else>Aucune prise</g:else>  <br/>
                            Nombre de personnes : <g:if
                                    test="${score.nbFishingMan > 0}">${score.nbFishingMan}</g:if><g:else>Aucun pêcheur abonné</g:else> <br/>
                        </p>
                    </div>

                    <div class="col-md-offset-2 col-md-2">
                        <g:form url="[action: 'suscribeUnsuscribeToArea', controller: 'fishingArea', resource: fishingAreaInstance]">
                            <h2 style="float:right"><button type="submit" class="btn btn-success"
                                                            style="width:115px;weight:35px"><g:if
                                        test="${isAlreadySuscribing}">Ne plus suivre</g:if><g:else>Suivre</g:else></button>
                            </h2>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-offset-2 col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Description</h4>
                </div>

                <div class="panel-body">
                    ${fishingAreaInstance.description}
                </div>

            </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Votre note</h4>
                </div>

                <div class="panel-body">
                    <g:form controller="fishingArea" action="note">
                        <select id="note" name="note" class="selectpicker select-note">
                            <option value="1" <g:if test="${noteGiven == 1}">selected</g:if>>1</option>
                            <option value="2" <g:if test="${noteGiven == 2}">selected</g:if>>2</option>
                            <option value="3" <g:if test="${noteGiven == 3}">selected</g:if>>3</option>
                            <option value="4" <g:if test="${noteGiven == 4}">selected</g:if>>4</option>
                            <option value="5" <g:if test="${noteGiven == 5}">selected</g:if>>5</option>
                        </select>
                        <g:hiddenField name="fishingArea" value="${fishingAreaInstance.id}"/>
                        <g:if test="${noteGiven > 0}">
                            <g:submitButton name="submit" class="btn btn-primary" value="Changer"/>
                        </g:if>
                        <g:else>
                            <g:submitButton name="submit" class="btn btn-primary" value="Noter"/>
                        </g:else>
                    </g:form>
                </div>

            </div>

            <h2>Derniers commentaires</h2>

            <div id="comment5"><g:include controller="comment" action="show5Comments"
                                          params="[commentable: fishingAreaInstance]"/></div>

            <div class="more"><button class="btn btn-link"
                                      onclick="showModal(event, ${fishingAreaInstance.id});">Afficher tous les commentaires</button>
            </div>

            <div id="alt-com"><p>Aucun commentaire<br/></p></div>
            <g:render template="../comment/createComment"
                      model="[commentable: fishingAreaInstance, fishingMan: session.fishingMan, formid: 'sub0']"/>
        </div>

        <div class="col-md-4">
            <g:if test="${isAlreadySuscribing}">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <h4>Vous avez eu une prise ? <g:link action="shareFromArea" controller="catch"
                                                                     class="btn btn-success" id="${fishingAreaInstance.id}">
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
                                  model="[singleCatch: singleCatch.aCatch, nbComments: singleCatch.nbComments]"/>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</div>
<script>
    if ($("#comment5")[0].innerHTML == "") {
        $(".more")[0].style.display = "none";
    }
    else {
        $('#alt-com')[0].style.display = "none";
    }
</script>
</body>
</html>
