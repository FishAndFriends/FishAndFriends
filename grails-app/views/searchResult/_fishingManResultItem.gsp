<div class="jumbotron search-block">
    <p>

    <div class="icon-cust">
        <g:link action="show" controller="fishingMan" id="${r.id}">
            <h2><i class="fa fa-user"></i> ${r.firstname} ${r.lastname}</h2>
        </g:link>
    </div>

<br/>
<br/>
Email : ${r.email} <br/>
Sexe :
<g:if test="${r.gender == "H"}">
    Homme
</g:if>
<g:else>
    Femme
</g:else>

</p>


</div>