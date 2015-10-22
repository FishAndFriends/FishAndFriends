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
                        <h2 style="float:right"><button type="button" class="btn btn-success">Suivre</button></h2>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-offset-2 col-md-4">
            <div class="homebox jumbotron">
                <h2>Description</h2>

                ${fishingAreaInstance.description}
                <br/>
                <br/>

                <h2>Note</h2>
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
                        <g:submitButton name="submit" class="btn btn-primary" value="Changer" />
                    </g:if>
                    <g:else>
                        <g:submitButton name="submit" class="btn btn-primary" value="Noter" />
                    </g:else>
                </g:form>
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
            <g:set var="counter" value="${1}"/>
            <g:each var="singleCatch" in="${catches}">
                <div class="row">
                    <div class="col-md-12">
                        <g:render template="showCatch"
                                  model="[singleCatch: singleCatch.aCatch, counter: counter, nbComments: singleCatch.nbComments]"/>
                    </div>
                </div>
                <g:set var="counter" value="${counter + 1}"/>
            </g:each>
        </div>
    </div>
</div>
</div>

<!-- Modal -->
<div id="commentModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Liste des commentaires</h4>
            </div>

            <div class="modal-body custom_modal-body">
                <div id="modal-comment-body">
                    <!-- Contains all comments provided by AJAX -->
                </div>
                <g:render template="../comment/createComment"
                          model="[commentable: fishingAreaInstance, fishingMan: session.fishingMan, formid: 'sub1']"/>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script>
    function showModal(event, ident) {
        event.preventDefault();

        $('#commentModal').modal('show');

        // Change the value of the commentable.id
        $("[name = 'commentable.id']").val(ident);

        // Retrieve all comments for the commentable
        $.ajax({
            type: 'POST',
            url: '${createLink(action: "showAllComment", controller: "comment")}',
            data: {'commentable': ident},
            success: function (data) {
                $("#modal-comment-body").html(data);
            }
        });
    }

    // Change the "Show more" according to the number of comments
    if ($("#comment5")[0].innerHTML == "") {
        $(".more")[0].style.display = "none";
    }
    else {
        $('#alt-com')[0].style.display = "none";
    }

    // Compute the size of the modal
    $(window).resize(function () {
        $(".custom_modal-body")[0].style.height = ($(window).height() - 185) + "px";
    });
    $(".custom_modal-body")[0].style.height = ($(window).height() - 185) + "px";

    // Setup for comments for catches
    var catches = $(".showComment");
    for (var i = 0; i < catches.length; i++) {
        catches[i].onclick = function (event) {
            showModal(event, this.dataset.ident)
        };
    }
</script>
</body>
</html>
