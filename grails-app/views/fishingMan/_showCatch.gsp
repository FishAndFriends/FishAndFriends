<div class="panel panel-success catchPanel">
    <div class="panel-heading">
        <b>${singleCatch.fishingArea.name}, ${singleCatch.fishingArea.location}</b>
    </div>

    <div class="panel-body">
        Par : ${singleCatch.fishingMan.firstname} ${singleCatch.fishingMan.lastname}<br/>
        Le : <g:formatDate format="yyyy-MM-dd HH:mm" date="${singleCatch.date}"/> <br/>
        Poisson péché : <b>${singleCatch.fish.name}</b>
        <ul>
            <li>Poids: ${singleCatch.weight} kg</li>
            <li>Taille: ${singleCatch.size} cm</li>
        </ul>
       <%-- <button class="btn btn-link showComment" data-ident="${singleCatch.id}">
            <g:if test="${nbComments > 1}">${nbComments} commentaires</g:if>
            <g:elseif test="${nbComments == 1}">${nbComments} commentaire</g:elseif>
            <g:else>Aucun commentaire. Soyez le premier commenter</g:else>
        </button> --%>
    </div>
</div>