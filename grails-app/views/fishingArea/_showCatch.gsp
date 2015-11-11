<div class="panel panel-info catchPanel">
    <div class="panel-heading">
        <i class="fa fa-user"></i> <b><g:link action="show" controller="fishingMan"
                                              id="${singleCatch.fishingMan.id}">${singleCatch.fishingMan.firstname} ${singleCatch.fishingMan.lastname}</b></g:link>
        <div class="pull-right">Le <g:formatDate format="dd MMM yyyy à HH:mm" date="${singleCatch.date}"/></div>
    </div>

    <div class="panel-body">
        Poisson péché : <b>${singleCatch.fish.name}</b>
        <ul>
            <li>Poids: ${singleCatch.weight} kg</li>
            <li>Taille: ${singleCatch.size} cm</li>
        </ul>
    </div>

    <div class="panel-footer">
        <a href="#" class="showComment" data-ident="${singleCatch.id}">
            <g:if test="${nbComments > 1}">${nbComments} commentaires</g:if>
            <g:elseif test="${nbComments == 1}">${nbComments} commentaire</g:elseif>
            <g:else>Aucun commentaire. Soyez le premier à commenter</g:else>
        </a>
    </div>
</div>
